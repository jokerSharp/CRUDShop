package com.shop.PetProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
@ToString
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
}
