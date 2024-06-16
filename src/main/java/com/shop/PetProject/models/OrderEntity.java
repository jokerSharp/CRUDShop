package com.shop.PetProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@ToString
@Entity
@Table(name = "order_entity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private OrderStatuses status;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
}
