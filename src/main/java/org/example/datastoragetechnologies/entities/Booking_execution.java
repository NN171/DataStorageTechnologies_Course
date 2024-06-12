package org.example.datastoragetechnologies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "booking_execution")
public class Booking_execution {
    @Id
    @Column(insertable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operationId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false)
    private double repairCost;

    @Column(nullable = false)
    private LocalDate executeDate;

    @Column(nullable = false)
    private boolean messageToClient;

    @Column(nullable = false)
    private String repairType;

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(double repairCost) {
        this.repairCost = repairCost;
    }

    public LocalDate getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(LocalDate executeDate) {
        this.executeDate = executeDate;
    }

    public boolean isMessageToClient() {
        return messageToClient;
    }

    public void setMessageToClient(boolean messageToClient) {
        this.messageToClient = messageToClient;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }
}
