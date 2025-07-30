package com.mikadev.autocontrolapi.sparePartUsed;

import com.mikadev.autocontrolapi.repairOrder.RepairOrderEntity;
import com.mikadev.autocontrolapi.sparePart.SparePartEntity;
import com.mikadev.autocontrolapi.user.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SparePartUsedMapper {
    public static SparePartUsedGetDTO toDTO(SparePartUsedEntity entity) {
        return new SparePartUsedGetDTO(
                entity.getId(),
                entity.getRepairOrder().getId(),
                entity.getSparePart().getId(),
                entity.getUnitPrice(),
                entity.getQuantity(),
                entity.getTotalPrice(),
                entity.getCreatedBy().getId(),
                entity.getCreatedAt(),
                entity.getUpdatedBy() != null ? entity.getUpdatedBy().getId() : null,
                entity.getUpdatedAt()
        );
    }

    public static SparePartUsedEntity toEntityPost(SparePartUsedPostDTO dto,
                                                   RepairOrderEntity repairOrder,
                                                   SparePartEntity sparePart,
                                                   UserEntity createdBy) {
        SparePartUsedEntity entity = new SparePartUsedEntity();
        entity.setRepairOrder(repairOrder);
        entity.setSparePart(sparePart);
        entity.setUnitPrice(dto.unitPrice());
        entity.setQuantity(dto.quantity());
        entity.setTotalPrice(dto.totalPrice());
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public static void toEntityPut(SparePartUsedEntity entity,
                                   SparePartUsedPutDTO dto,
                                   RepairOrderEntity repairOrder,
                                   SparePartEntity sparePart,
                                   UserEntity updatedBy) {
        entity.setRepairOrder(repairOrder);
        entity.setSparePart(sparePart);
        entity.setUnitPrice(dto.unitPrice());
        entity.setQuantity(dto.quantity());
        entity.setTotalPrice(dto.totalPrice());
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
