package com.mikadev.autocontrolapi.mechanics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicRepository extends JpaRepository<MechanicEntity, Long> {
}
