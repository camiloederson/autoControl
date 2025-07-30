package com.mikadev.autocontrolapi.sparePart;

import com.mikadev.autocontrolapi.sparePartBrand.SparePartBrandEntity;
import com.mikadev.autocontrolapi.sparePartCategory.SparePartCategoryEntity;
import com.mikadev.autocontrolapi.user.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SparePartMapper {
    public SparePartGetDTO toDTO(SparePartEntity entity) {
        return new SparePartGetDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock(),
                entity.getCategory() != null ? entity.getCategory().getName() : null,
                entity.getSpareBrand() != null ? entity.getSpareBrand().getName() : null,
                entity.getModel(),
                entity.isActive(),
                entity.getCreatedBy() != null ? entity.getCreatedBy().getName() : null,
                entity.getUpdatedBy() != null ? entity.getUpdatedBy().getName() : null,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public SparePartEntity toSaveEntity(SparePartPostDTO dto, SparePartCategoryEntity category,
                                        SparePartBrandEntity brand, UserEntity createdBy) {
        SparePartEntity entity = new SparePartEntity();
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setStock(dto.stock());
        entity.setCategory(category);
        entity.setSpareBrand(brand);
        entity.setModel(dto.model());
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setActive(true);
        return entity;
    }

    public SparePartEntity toPutEntity(SparePartEntity existing, SparePartPutDTO dto,
                                       SparePartCategoryEntity category, SparePartBrandEntity brand,
                                       UserEntity updatedBy) {
        existing.setName(dto.name());
        existing.setDescription(dto.description());
        existing.setPrice(dto.price());
        existing.setStock(dto.stock());
        existing.setCategory(category);
        existing.setSpareBrand(brand);
        existing.setModel(dto.model());
        existing.setUpdatedBy(updatedBy);
        existing.setUpdatedAt(LocalDateTime.now());
        return existing;
    }
}
