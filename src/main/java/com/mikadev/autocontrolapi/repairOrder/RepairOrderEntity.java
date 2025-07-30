package com.mikadev.autocontrolapi.repairOrder;

import com.mikadev.autocontrolapi.customer.CustomerEntity;
import com.mikadev.autocontrolapi.mechanics.MechanicEntity;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.vehicle.VehicleEntity;
import com.mikadev.autocontrolapi.vehicleStatus.VehicleStatusEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "repair_orders")
public class RepairOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id", nullable = false)
    private MechanicEntity mechanic;

    @Column(name = "estimated_value")
    private BigDecimal estimatedValue;

    @Column(name = "final_value")
    private BigDecimal finalValue;

    @Column(columnDefinition = "TEXT")
    private String diagnosis;

    @Column(name = "reported_issue", columnDefinition = "TEXT")
    private String reportedIssue;

    @Column(columnDefinition = "TEXT")
    private String workDescription;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_status_id", nullable = false)
    private VehicleStatusEntity status;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @Column(name = "exit_date")
    private LocalDateTime exitDate;

    @Column(name = "is_closed")
    private Boolean isClosed = false;

    @Column(name = "invoice_generated")
    private Boolean invoiceGenerated = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    private UserEntity createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private UserEntity updatedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
