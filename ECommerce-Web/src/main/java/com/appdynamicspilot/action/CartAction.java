/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appdynamicspilot.action;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appdynamicspilot.jms.FulfillmentProducer;
import com.appdynamicspilot.model.FulfillmentOrder;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.appdynamics.xml.CastorUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import com.appdynamicspilot.exception.OrderException;
import com.appdynamicspilot.jms.CustomerMessageProducer;
import com.appdynamicspilot.jms.MessageProducer;
import com.appdynamicspilot.model.Cart;
import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.model.User;
import com.appdynamicspilot.webserviceclient.*;
import com.appdynamicspilot.service.CartService;
import com.appdynamicspilot.service.ItemService;
import com.appdynamicspilot.util.ArgumentUtils;

import org.tempuri.*;

public class CartAction extends ActionSupport implements Preparable,
        ServletResponseAware, ServletRequestAware {
    private static final Logger log = Logger.getLogger(CartAction.class);
    private CartService cartService;
    private ItemService itemService;
    private String selectedItemId;
    private String xml;
    private MessageProducer messageProducer;
    private FulfillmentProducer fulfillmentProducer;
    private CustomerMessageProducer customerMessageProducer;
    private HttpServletRequest request;
    private HttpServletResponse response;
    List<Cart> cartsList;

    private boolean checkMe;

    public boolean isCheckMe() {
        return checkMe;
    }

    public void setCheckMe(boolean checkMe) {
        this.checkMe = checkMe;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public List<Cart> getCartsList() {
        return cartsList;
    }

    public void setCartsList(List<Cart> cartsList) {
        this.cartsList = cartsList;
    }

    public String addToCart() {
        User user = (User) ActionContext.getContext().getSession()
                .get("USER");
        if (ArgumentUtils.isNull(user))
            return "LOGOUT";
        //cartService.deleteCartItems(user.getId());
        if ("".equals(selectedItemId))
            return "FAILURE";
        if (selectedItemId.charAt(0) == ',')
            selectedItemId = selectedItemId.substring(1);
        String[] selectedItemIds = selectedItemId.split(",");
        String load = getServletRequest().getParameter("load");
        log.debug("load >>>>>>>" + load);
        String delay = getServletRequest().getParameter("delay");
        log.debug("delay >>>>>>>>>>>>>" + delay);
        String error = getServletRequest().getParameter("error");
        log.debug("error param is >>>>>>>>>" + error);
        getServletRequest().getSession().setAttribute("error", error);
        boolean sleep = (!ArgumentUtils.isNullOrEmpty(load));
        int sleepTime = 0;
        if (sleep) {
            try {
                sleepTime = Integer.parseInt(delay);
            } catch (NumberFormatException e) {
                // eat exception in case of delay is wrong!!!
            }
        }
        log.debug("Sleep time is >>>>>>>" + sleepTime + ">>>>>>>>>>>>>> sleep="
                + sleep);
        for (int i = 0; i < selectedItemIds.length; i++) {
            if (sleep) {
                log.debug("Sleep time is>>>>" + i * sleepTime);
                try {
                    /**
                     * Adding thread.sleep to demo strate slowBTs. for every
                     * transaction
                     */
                    Thread.sleep(i * sleepTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Item item = itemService.getItemByID(Long
                    .parseLong(selectedItemIds[i]));
            if (item != null) {
                Cart cart = (Cart) getServletRequest().getSession().getAttribute("CART");
                if (cart == null) {
                    cart = new Cart();
                }
                getServletRequest().getSession().setAttribute("CART", cart);
                cart.addItem(item);
                //trigger the mdic
                cart.getCartTotal();
                cart.setUser(user);
                cartService.saveItemInCart(cart);
            }

        }
        List<Item> cartsList = cartService.getAllItemsByUser(user.getId());
        request.setAttribute("cartsList", cartsList);
        return "SUCCESS";
    }

    public String addToCartXML() {
        User user = (User) ActionContext.getContext().getSession()
                .get("USER");
        if (ArgumentUtils.isNull(user)) {
            return "LOGOUT";
        }

        cartService.deleteCartItems(user.getId());
        if ("".equals(xml))
            return "FAILURE";
        CastorUtil cu = new CastorUtil();
        cu.saveCartItems(xml);
        List<Item> cartsList = cartService.getAllItemsByUser(user.getId());
        request.setAttribute("cartsList", cartsList);
        return "SUCCESS";
    }

    public String sendItems() {
        String fakeAmount = (String) ActionContext.getContext().get("orderAmount");

        User user = (User) ActionContext.getContext().getSession()
                .get("USER");

        if (ArgumentUtils.isNull(user)) {
            return "LOGOUT";
        }

        Long userId = user.getId();
        ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        org.tempuri.ArrayOfOrderDetail arrayOfOrderDetail = new org.tempuri.ArrayOfOrderDetail();
        Cart cart = (Cart) ActionContext.getContext().getSession()
                .get("CART");
        List<Item> cartList = null;
        if (fakeAmount!= null) {
            cart.setAmount(Double.valueOf(fakeAmount));
        }
        if (cart != null) {
            cartList = cartService.getAllItemsByUser(user.getId());
            //trigger the mdic
            cart.getCartTotal();
        } else {
            cartList = Collections.EMPTY_LIST;
        }
        String orderIds = "";
        String str1 = "";
        String invoiceId = "";
        String invoiceIds = "";
        int outOfStock = 0;

        for (Item item : cartList) {
            try {
                /**
                 * Following code block is making fake call in order to generate
                 * the exception and second method will make actual to inventory
                 * server
                 */
                String error = (String) getServletRequest().getSession()
                        .getAttribute("error");
                log.debug("PARAM is >>>>>>>>>>>" + error);
                if ("true".equalsIgnoreCase(error)) {
                    try {
                        callOrderService(cartService, item.getId(),
                                "http://localhost:8080/services/OrderService?");
                        getServletRequest().getSession().removeAttribute(
                                "error");
                    } catch (OrderException e) {
                        log.error(
                                "Something went wrong at inventory server ... retrying again!!!!!",
                                e.getT().fillInStackTrace());
                        log.error(e.getMessage());
                        // log.debug("Something went wrong at inventory server ... retrying again!!!!!");
                    }
                }
                Long id = cartService.checkOut(item.getId(),
                        cartService.getCartSize(userId));

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(id);
                orderDetail.setId(item.getId());
                orderDetail.setTitle(item.getTitle());
                /*
                 * invoiceId = generateInvoice(orderDetail);
				 * 
				 * invoiceIds = invoiceIds+invoiceId.toString()+", ";
				 */

                // orderDetailList.add(orderDetail);

                arrayOfOrderDetail.getOrderDetail().add(orderDetail);


                orderIds = orderIds + id.toString() + ", ";
                if (id == 0) {
                    outOfStock = 1;
                }
                FulfillmentOrder order = new FulfillmentOrder(item,user);
                fulfillmentProducer.sendFulfillment(order);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                request.setAttribute("msg",
                        "Error in creating order " + e.getMessage());
            }

        }

        if (checkMe) {
            invoiceId = generateInvoice(arrayOfOrderDetail);
        }

        log.debug("ORDERS ARE " + orderIds);
        if (!ArgumentUtils.isNullOrEmpty(orderIds) && outOfStock == 0) {
            orderIds = orderIds.substring(0, orderIds.length() - 2);
            log.debug("**************** Request time(ms) in CartAction:sendItems :: "
                    + System.currentTimeMillis());
            messageProducer.sendMessageWithOrderId(orderIds, user.getEmail());
            messageProducer.sendTextMessageWithOrderId();
            customerMessageProducer.sendCustomerMesssage(user);


            if (invoiceId == "") {
                request.setAttribute("msg", "Order ID(s) for your order(s) : "
                        + orderIds);
            } else {
                request.setAttribute("msg",
                        "Your Invoice ID for your order(s) " + orderIds + ": "
                                + invoiceId);
            }
        } else {
            request.setAttribute("msg",
                    "Order not created as one or more items in your cart were out of stock");
        }
        cartService.deleteCart(cart);
        cart = new Cart();
        cart.setUser(user);
        ActionContext.getContext().getSession().put("CART",cart);
        return "ENDPAGE";
    }

    private static String generateInvoice(ArrayOfOrderDetail orderDetail) {
        DotNetClient client = new DotNetClient();
        return client.callService(orderDetail);
    }

    public void prepare() throws Exception {
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public CartService getCartService() {
        return this.cartService;
    }

    public MessageProducer getMessageProducer() {
        return this.messageProducer;
    }

    public String getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(String selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * @param messageProducer The messageProducer to set.
     */
    public void setMessageProducer(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getServletRequest() {
        return request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getServletResponse() {
        return response;
    }

    public void callOrderService(CartService cartService, Long itemId,
                                 String url) throws OrderException {
        User user = (User) ActionContext.getContext().getSession()
                .get("USER");
        try {
            log.debug(">>>>>>>>>.doing checkout with error Param<<<<<<<<<<<<<<<");
            cartService.checkOut(itemId, cartService.getCartSize(user.getId()), url);
            // removing error flag
            getServletRequest().getSession().removeAttribute("error");
        } catch (Exception e) {
            throw new OrderException(
                    "error in creating order on inventory server",
                    e.fillInStackTrace());
            // log.debug("Something went wrong at inventory server ... retrying again!!!!!");
        }
    }

    public CustomerMessageProducer getCustomerMessageProducer() {
        return customerMessageProducer;
    }

    public void setCustomerMessageProducer(CustomerMessageProducer customerMessageProducer) {
        this.customerMessageProducer = customerMessageProducer;
    }

    public FulfillmentProducer getFulfillmentProducer() {
        return fulfillmentProducer;
    }

    public void setFulfillmentProducer(FulfillmentProducer fulfillmentProducer) {
        this.fulfillmentProducer = fulfillmentProducer;
    }

    public String removeAllItems() {
        Cart cart = (Cart) request.getSession().getAttribute("CART");
        User user = (User) request.getSession().getAttribute("USER");
        List<Item> items = cart.getItems(); {
            for (Item item:items) {
                cartService.deleteItemInCart(user.getEmail(), item.getId());
            }
        }
        return "SUCCESS";
    }

}
