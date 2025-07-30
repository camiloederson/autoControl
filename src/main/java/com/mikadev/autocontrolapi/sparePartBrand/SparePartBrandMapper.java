package com.mikadev.autocontrolapi.sparePartBrand;

import org.springframework.stereotype.Component;

@Component
public class SparePartBrandMapper {
    public SparePartBrandGetDTO toDTO(SparePartBrandEntity sparePartBrandEntity){
            return new SparePartBrandGetDTO(
                    sparePartBrandEntity.getId(),
                    sparePartBrandEntity.getName(),
                    sparePartBrandEntity.getDescription(),
                    sparePartBrandEntity.getCreatedBy() != null ? sparePartBrandEntity.getCreatedBy().getName() : null,
                    sparePartBrandEntity.getUpdatedBy() != null ? sparePartBrandEntity.getUpdatedBy().getName() : null,
                    sparePartBrandEntity.getCreatedAt(),
                    sparePartBrandEntity.getUpdatedAt());
    }
}
