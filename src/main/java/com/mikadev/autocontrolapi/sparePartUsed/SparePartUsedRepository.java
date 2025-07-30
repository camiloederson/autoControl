package com.mikadev.autocontrolapi.sparePartUsed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartUsedRepository extends JpaRepository<SparePartUsedEntity, Long> {
}
