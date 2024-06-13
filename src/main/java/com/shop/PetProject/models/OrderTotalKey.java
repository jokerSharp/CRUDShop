package com.shop.PetProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTotalKey implements Serializable {

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private UUID productId;

}
