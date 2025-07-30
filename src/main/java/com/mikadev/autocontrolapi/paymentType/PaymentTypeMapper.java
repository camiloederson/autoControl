package com.mikadev.autocontrolapi.paymentType;

import com.mikadev.autocontrolapi.user.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentTypeMapper {
    public static PaymentTypeGetDTO toDTO(PaymentTypeEntity entity) {
        return new PaymentTypeGetDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreatedBy().getId(),
                entity.getCreatedAt(),
                entity.getUpdatedBy() != null ? entity.getUpdatedBy().getId() : null,
                entity.getUpdatedAt()
        );
    }

    public static PaymentTypeEntity toEntityPost(PaymentTypePostDTO dto, UserEntity createdBy) {
        PaymentTypeEntity entity = new PaymentTypeEntity();
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public static void toEntityPut(PaymentTypeEntity entity, PaymentTypePutDTO dto, UserEntity updatedBy) {
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
