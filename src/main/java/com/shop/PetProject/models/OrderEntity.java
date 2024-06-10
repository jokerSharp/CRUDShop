package com.shop.PetProject.models;

import jakarta.persistence.*;
import lombok.*;

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
    @OneToMany(mappedBy = "orderEntity")
//    @JoinColumn(name = "order_id")
    private Set<OrderTotal> orderTotals;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private OrderStatuses status;
    @Transient
    private Integer totalPrice;
}
