package com.mikadev.autocontrolapi.repairOrder;

import com.mikadev.autocontrolapi.customer.CustomerEntity;
import com.mikadev.autocontrolapi.mechanics.MechanicEntity;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.vehicle.VehicleEntity;
import com.mikadev.autocontrolapi.vehicleStatus.VehicleStatusEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RepairOrderMapper {
    public static RepairOrderGetDTO toDTO(RepairOrderEntity entity) {
        return new RepairOrderGetDTO(
                entity.getId(),
                entity.getCustomer().getId(),
                entity.getVehicle().getId(),
                entity.getMechanic().getId(),
                entity.getEstimatedValue(),
                entity.getFinalValue(),
                entity.getDiagnosis(),
                entity.getReportedIssue(),
                entity.getWorkDescription(),
                entity.getObservations(),
                entity.getStatus().getId(),
                entity.getEntryDate(),
                entity.getExitDate(),
                entity.getIsClosed(),
                entity.getInvoiceGenerated(),
                entity.getCreatedBy().getId(),
                entity.getUpdatedBy() != null ? entity.getUpdatedBy().getId() : null,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static RepairOrderEntity toEntityPost(RepairOrderPostDTO dto,
                                                 CustomerEntity customer,
                                                 VehicleEntity vehicle,
                                                 MechanicEntity mechanic,
                                                 VehicleStatusEntity status,
                                                 UserEntity createdBy) {
        RepairOrderEntity entity = new RepairOrderEntity();
        entity.setCustomer(customer);
        entity.setVehicle(vehicle);
        entity.setMechanic(mechanic);
        entity.setEstimatedValue(dto.estimatedValue());
        entity.setFinalValue(dto.finalValue());
        entity.setDiagnosis(dto.diagnosis());
        entity.setReportedIssue(dto.reportedIssue());
        entity.setWorkDescription(dto.workDescription());
        entity.setObservations(dto.observations());
        entity.setStatus(status);
        entity.setEntryDate(dto.entryDate());
        entity.setExitDate(dto.exitDate());
        entity.setIsClosed(dto.isClosed() != null ? dto.isClosed() : false);
        entity.setInvoiceGenerated(dto.invoiceGenerated() != null ? dto.invoiceGenerated() : false);
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public static void toEntityPut(RepairOrderEntity entity,
                                   RepairOrderPutDTO dto,
                                   CustomerEntity customer,
                                   VehicleEntity vehicle,
                                   MechanicEntity mechanic,
                                   VehicleStatusEntity status,
                                   UserEntity updatedBy) {
        entity.setCustomer(customer);
        entity.setVehicle(vehicle);
        entity.setMechanic(mechanic);
        entity.setEstimatedValue(dto.estimatedValue());
        entity.setFinalValue(dto.finalValue());
        entity.setDiagnosis(dto.diagnosis());
        entity.setReportedIssue(dto.reportedIssue());
        entity.setWorkDescription(dto.workDescription());
        entity.setObservations(dto.observations());
        entity.setStatus(status);
        entity.setEntryDate(dto.entryDate());
        entity.setExitDate(dto.exitDate());
        entity.setIsClosed(dto.isClosed() != null ? dto.isClosed() : entity.getIsClosed());
        entity.setInvoiceGenerated(dto.invoiceGenerated() != null ? dto.invoiceGenerated() : entity.getInvoiceGenerated());
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
