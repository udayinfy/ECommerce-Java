package com.appdynamics.inventory;

import com.appdynamicspilot.exception.InventoryServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class OrderWebservices {
    private static final Logger log = LoggerFactory.getLogger(OrderWebservices.class);

//    private com.appdynamics.inventory.OrderService orderServiceImpl;
//
//    public void setOrderServiceImpl(com.appdynamics.inventory.OrderService orderServiceImpl) {
//        this.orderServiceImpl = orderServiceImpl;
//    }

     public void setOrderDao(OrderDao orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    private OrderDao orderServiceImpl;

    public Long createOrder(OrderRequest orderRequest) throws InventoryServerException {
        try {
            //System.out.println("^^^^^^^^^ Request time(ms) in AppdynamicsPilot-axis OrderWebservices ^^^^^^: " + System.currentTimeMillis());
            return orderServiceImpl.createOrder(orderRequest);
        } catch (InventoryServerException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return (long) 0;
        }
    }

    public Long createPO(Long itemId, Integer quantity) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItemId(itemId);
        orderRequest.setQuantity(quantity);
        try {
            return orderServiceImpl.createOrder(orderRequest);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return (long) 0;
        }
    }


}
