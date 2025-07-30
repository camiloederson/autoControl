package com.mikadev.autocontrolapi.vehicleStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleStatusRepository extends JpaRepository<VehicleStatusEntity, Long> {
}
