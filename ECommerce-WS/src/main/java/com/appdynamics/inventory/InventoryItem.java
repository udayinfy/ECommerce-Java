package com.appdynamics.inventory;

import javax.persistence.*;

/**
 * Created by aleftik on 10/12/14.
 */

@Entity
@Table(name="ITEM")
public class InventoryItem {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
