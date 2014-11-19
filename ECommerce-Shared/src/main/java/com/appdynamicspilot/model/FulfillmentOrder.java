package com.appdynamicspilot.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by aleftik on 11/14/14.
 */
@XmlRootElement(name = "fulfillment-order")
public class FulfillmentOrder {

    @XmlElement
    private Long id = null;
    @XmlElement
    private Double price = null;
    @XmlElement
    private String username = null;
    @XmlElement
    private Long userId = null;
    @XmlElement
    private User.CUSTOMER_TYPE type = null;


    FulfillmentOrder() {

    }

    public FulfillmentOrder(Item item,User user) {
        this.id = item.getId();
        this.price = item.getPrice();
        this.userId = user.getId();
        this.username = user.getEmail();
        this.type = user.getCustomerType();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User.CUSTOMER_TYPE getType() {
        return type;
    }

    public void setType(User.CUSTOMER_TYPE type) {
        this.type = type;
    }
}
