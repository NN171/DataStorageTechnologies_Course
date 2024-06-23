package org.example.datastoragetechnologies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "booking_execution")
public class BookingExecution {
    @Id
    @Column(insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operationId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "repair_cost", nullable = false)
    private double repairCost;

    @Column(nullable = false)
    private LocalDate executeDate;

    @Column(nullable = false)
    private boolean messageToClient;

    @Column(nullable = false)
    private String repairType;
}
