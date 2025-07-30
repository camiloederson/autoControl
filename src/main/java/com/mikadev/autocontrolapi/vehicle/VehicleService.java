package com.mikadev.autocontrolapi.vehicle;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.user.UserRepository;
import com.mikadev.autocontrolapi.vehicleBrand.VehicleBrandEntity;
import com.mikadev.autocontrolapi.vehicleBrand.VehicleBrandGetDTO;
import com.mikadev.autocontrolapi.vehicleBrand.VehicleBrandRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleBrandRepository vehicleBrandRepository;
    private final UserRepository userRepository;

    public VehicleService(VehicleRepository vehicleRepository,
                          VehicleBrandRepository vehicleBrandRepository,
                          UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleBrandRepository = vehicleBrandRepository;
        this.userRepository = userRepository;
    }

    // findById

    public VehicleGetDTO findById(Long id){
        VehicleEntity vehicleEntity = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle was not found"));

        VehicleBrandEntity vehicleBrandEntity = vehicleEntity.getBrand();

        return new VehicleGetDTO(
                vehicleEntity.getId(), vehicleEntity.getModel(),
                vehicleEntity.getColor(), vehicleEntity.getYear(),
                vehicleEntity.getLicensePlate(), new VehicleBrandNameDTO(vehicleBrandEntity.getName())
        );
    }

    // findAll

    public List<VehicleGetDTO> findAll(){
        List<VehicleEntity> vehicleEntityList = vehicleRepository.findAll();
        return vehicleEntityList.stream()
                .map( vehicleEntity -> new VehicleGetDTO(
                vehicleEntity.getId(), vehicleEntity.getModel(),
                vehicleEntity.getColor(), vehicleEntity.getYear(),
                vehicleEntity.getLicensePlate(), new VehicleBrandNameDTO(vehicleEntity.getBrand().getName())))
                .toList();
    }

    // save

    public VehicleGetDTO save(VehiclePostDTO vehicle){
        VehicleEntity vehicleEntity = new VehicleEntity();

        VehicleBrandEntity vehicleBrandEntity = vehicleBrandRepository.findById(vehicle.vehicleBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand does not exist"));
        UserEntity createdBy = userRepository.findById(vehicle.createdByUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));

        vehicleEntity.setColor(vehicle.color());
        vehicleEntity.setModel(vehicle.model());
        vehicleEntity.setYear(vehicle.year());
        vehicleEntity.setLicensePlate(vehicle.licensePlate());
        vehicleEntity.setCreatedAt(LocalDateTime.now());
        vehicleEntity.setBrand(vehicleBrandEntity);
        vehicleEntity.setCreatedBy(createdBy);

        VehicleEntity vehicleEntitySaved = vehicleRepository.save(vehicleEntity);
        VehicleBrandNameDTO vehicleBrandNameDTO = new VehicleBrandNameDTO(vehicleEntitySaved.getBrand().getName());
        return new VehicleGetDTO(
                vehicleEntitySaved.getId(), vehicleEntitySaved.getModel(),
                vehicleEntitySaved.getColor(), vehicleEntitySaved.getYear(),
                vehicleEntitySaved.getLicensePlate(), vehicleBrandNameDTO
        );
    }

    // Update

    public VehicleGetDTO update(Long id, VehicleUpdateDTO vehicle){

        VehicleEntity vehicleEntity = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle does not exist. Check the id"));

        // UpdatedBy
        UserEntity updatedBy = userRepository.findById(vehicle.updatedByUserId())
                .orElseThrow(() -> new ResourceNotFoundException("UpdatedByUser does not exist"));

        // VehicleBrand
        VehicleBrandEntity vehicleBrandEntity = vehicleBrandRepository.findById(vehicle.vehicleBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand does not exist"));

        vehicleEntity.setColor(vehicle.color());
        vehicleEntity.setLicensePlate(vehicle.licensePlate());
        vehicleEntity.setModel(vehicle.model());
        vehicleEntity.setYear(vehicle.year());
        vehicleEntity.setUpdatedAt(LocalDateTime.now());
        vehicleEntity.setUpdatedBy(updatedBy);
        vehicleEntity.setBrand(vehicleBrandEntity);
        VehicleEntity vehicleEntityUpdated = vehicleRepository.save(vehicleEntity);

        return new VehicleGetDTO(
                vehicleEntityUpdated.getId(), vehicleEntityUpdated.getModel(),
                vehicleEntityUpdated.getColor(), vehicleEntityUpdated.getYear(),
                vehicleEntityUpdated.getLicensePlate(),
                new VehicleBrandNameDTO(vehicleEntityUpdated.getBrand().getName()));

    }

    // delete

    public void deleteById(Long id){
        vehicleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle does not exist"));

        vehicleRepository.deleteById(id);

    }
}
