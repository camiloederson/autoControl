package com.mikadev.autocontrolapi.sparePartCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartCategoryRepository extends JpaRepository<SparePartCategoryEntity, Long> {
}
