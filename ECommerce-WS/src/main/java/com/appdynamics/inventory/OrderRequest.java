package com.appdynamics.inventory;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.math.BigDecimal;

public class OrderRequest {
    private Long itemId;
    private Integer quantity;


    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(itemId).append(quantity).toString();
    }
}
