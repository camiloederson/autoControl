package com.mikadev.autocontrolapi.paymentStatus;

import com.mikadev.autocontrolapi.user.UserEntity;

import java.time.LocalDateTime;

public class PaymentStatusMapper {
    public static PaymentStatusGetDTO toDTO(PaymentStatusEntity entity) {
        return new PaymentStatusGetDTO(
                entity.getId(),
                entity.getStatus(),
                entity.getDescription(),
                entity.getCreatedBy().getId(),
                entity.getCreatedAt(),
                entity.getUpdatedBy() != null ? entity.getUpdatedBy().getId() : null,
                entity.getUpdatedAt()
        );
    }

    public static PaymentStatusEntity toEntityPost(PaymentStatusPostDTO dto, UserEntity createdBy) {
        PaymentStatusEntity entity = new PaymentStatusEntity();
        entity.setStatus(dto.status());
        entity.setDescription(dto.description());
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public static void toEntityPut(PaymentStatusEntity entity, PaymentStatusPutDTO dto, UserEntity updatedBy) {
        entity.setStatus(dto.status());
        entity.setDescription(dto.description());
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
