package com.appdynamics.inventory;

import com.appdynamicspilot.model.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    protected Long id;
    protected Integer quantity;
    @Temporal(value = TemporalType.DATE)
    protected Date createdOn;

    protected InventoryItem item;

    public Order() {

    }

    public Order(OrderRequest orderRequest, InventoryItem item) {
        this.quantity = orderRequest.getQuantity();
        this.createdOn = new Date();
        this.item = item;
    }

    /**
     * @return Returns the createdOn.
     */
    public Date getCreatedOn() {
        return createdOn;
    }


    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }


    /**
     * @return Returns the quantity.
     */

    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param createdOn The createdOn to set.
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * @param quantity The quantity to set.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
