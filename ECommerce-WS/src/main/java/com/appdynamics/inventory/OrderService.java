package com.appdynamics.inventory;

import com.appdynamicspilot.exception.InventoryServerException;
import org.apache.log4j.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.math.BigDecimal;

@WebService
public class OrderService {
    private static final Logger log = Logger.getLogger(OrderService.class);
    private OrderDao orderDao;

    /**
     * @param orderDao The orderDao to set.
     */
    @WebMethod(exclude = true)
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @WebMethod
    public Long createOrder(OrderRequest orderRequest) throws InventoryServerException {
        try {
            log.info("creating order with order request: " + orderRequest.toString());
            return orderDao.createOrder(orderRequest);
        } catch (InventoryServerException e) {
            log.error("Error in creating order [" + orderRequest.toString() + "]");
            throw e;
        } catch (Exception e) {
            log.error("Error in creating order [" + orderRequest.toString() + "]");
            return (long) 0;
        }
    }

    @WebMethod
    public Long createPO(Long itemId, Integer quantity) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItemId(itemId);
        orderRequest.setQuantity(quantity);
        log.info("creating order with order request: " + orderRequest.toString());
        try {
            return orderDao.createOrder(orderRequest);
        } catch (Exception e) {
            log.error("Error in creating order [" + orderRequest.toString() + "]");
            return (long) 0;
        }
    }
}
