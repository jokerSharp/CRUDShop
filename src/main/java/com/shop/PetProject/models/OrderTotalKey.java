package com.shop.PetProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@Embeddable
public class OrderTotalKey implements Serializable {

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private UUID productId;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        OrderTotalKey that = (OrderTotalKey) o;
//        return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(orderId, productId);
//    }
}
