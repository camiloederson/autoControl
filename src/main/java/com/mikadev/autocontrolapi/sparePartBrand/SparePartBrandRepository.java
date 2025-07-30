package com.mikadev.autocontrolapi.sparePartBrand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartBrandRepository extends JpaRepository<SparePartBrandEntity, Long> {
}
