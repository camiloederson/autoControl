package com.mikadev.autocontrolapi.sparePartCategory;

import com.mikadev.autocontrolapi.user.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SparePartCategoryMapper {

    public SparePartCategoryGetDTO toDTO(SparePartCategoryEntity sparePartCategoryEntity) {
        return new SparePartCategoryGetDTO(
                sparePartCategoryEntity.getId(),
                sparePartCategoryEntity.getName(),
                sparePartCategoryEntity.getDescription(),
                sparePartCategoryEntity.getCreatedBy() != null ? sparePartCategoryEntity.getName() : null,
                sparePartCategoryEntity.getUpdatedBy() != null ? sparePartCategoryEntity.getName(): null,
                sparePartCategoryEntity.isActive(),
                sparePartCategoryEntity.getCreatedAt(),
                sparePartCategoryEntity.getUpdatedAt());
    }

    public SparePartCategoryEntity toEntity(SparePartCategoryPostDTO sparePartCategoryPostDTO, UserEntity createdBy) {
        SparePartCategoryEntity sparePartCategoryEntity = new SparePartCategoryEntity();
        sparePartCategoryEntity.setName(sparePartCategoryPostDTO.name());
        sparePartCategoryEntity.setDescription(sparePartCategoryPostDTO.description());
        sparePartCategoryEntity.setCreatedBy(createdBy);
        sparePartCategoryEntity.setCreatedAt(LocalDateTime.now());
        return sparePartCategoryEntity;
    }

    public SparePartCategoryEntity updateEntity(SparePartCategoryPutDTO sparePartCategoryPutDTO, UserEntity updatedBy) {
        SparePartCategoryEntity sparePartCategoryEntity = new SparePartCategoryEntity();
        sparePartCategoryEntity.setName(sparePartCategoryPutDTO.name());
        sparePartCategoryEntity.setDescription(sparePartCategoryPutDTO.description());
        sparePartCategoryEntity.setUpdatedBy(updatedBy);
        sparePartCategoryEntity.setUpdatedAt(LocalDateTime.now());

        return sparePartCategoryEntity;
    }
}
