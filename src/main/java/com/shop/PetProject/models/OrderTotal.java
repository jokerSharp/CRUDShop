package com.shop.PetProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@ToString
@Entity
@Table(name = "order_total")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTotal {

    @EmbeddedId
    private OrderTotalKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total")
    private BigDecimal total;
}
