package com.shop.PetProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    @Enumerated(EnumType.ORDINAL)
    private Categories category;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "article")
    private int article;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "quantity_change")
    private LocalDateTime quantityChange;
    @Column(name = "is_available")
    private Boolean isAvailable;
    @OneToMany(mappedBy = "product")
    private Set<OrderTotal> orderTotals;
}
