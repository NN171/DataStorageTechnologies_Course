package org.example.datastoragetechnologies.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column
    private String model;

    @Column(name = "product_type")
    private String productType;

    @Column(nullable = false)
    private String producer;
}
