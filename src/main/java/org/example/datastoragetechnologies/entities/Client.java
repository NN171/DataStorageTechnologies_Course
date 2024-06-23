package org.example.datastoragetechnologies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "client_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "full_name", nullable = false)
    private String fullName;
}
