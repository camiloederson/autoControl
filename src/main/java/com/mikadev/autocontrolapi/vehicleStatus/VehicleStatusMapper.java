package com.mikadev.autocontrolapi.vehicleStatus;

import com.mikadev.autocontrolapi.user.UserEntity;

import java.time.LocalDateTime;

public class VehicleStatusMapper {
    public static VehicleStatusGetDTO toGetDTO(VehicleStatusEntity entity) {
        return new VehicleStatusGetDTO(
                entity.getId(),
                entity.getStatus(),
                entity.getDescription(),
                entity.getCreatedBy() != null ? entity.getCreatedBy().getId() : null,
                entity.getUpdatedBy() != null ? entity.getUpdatedBy().getId() : null,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static VehicleStatusEntity toEntity(VehicleStatusPostDTO dto, UserEntity createdBy) {
        VehicleStatusEntity entity = new VehicleStatusEntity();
        entity.setStatus(dto.status());
        entity.setDescription(dto.description());
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public static void updateEntity(VehicleStatusEntity entity, VehicleStatusPutDTO dto, UserEntity updatedBy) {
        entity.setStatus(dto.status());
        entity.setDescription(dto.description());
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
