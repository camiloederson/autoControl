package com.mikadev.autocontrolapi.repairOrder;

import com.mikadev.autocontrolapi.customer.CustomerEntity;
import com.mikadev.autocontrolapi.customer.CustomerRepository;
import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.mechanics.MechanicEntity;
import com.mikadev.autocontrolapi.mechanics.MechanicRepository;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.user.UserRepository;
import com.mikadev.autocontrolapi.vehicle.VehicleEntity;
import com.mikadev.autocontrolapi.vehicle.VehicleRepository;
import com.mikadev.autocontrolapi.vehicleStatus.VehicleStatusEntity;
import com.mikadev.autocontrolapi.vehicleStatus.VehicleStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RepairOrderService {
    private final RepairOrderRepository repairOrderRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final MechanicRepository mechanicRepository;
    private final VehicleStatusRepository vehicleStatusRepository;
    private final UserRepository userRepository;

    public List<RepairOrderGetDTO> findAll() {
        List<RepairOrderEntity> entities = repairOrderRepository.findAll();
        List<RepairOrderGetDTO> dtos = new ArrayList<>();
        for (RepairOrderEntity entity : entities) {
            dtos.add(RepairOrderMapper.toDTO(entity));
        }
        return dtos;
    }

    public RepairOrderGetDTO findById(Long id) {
        RepairOrderEntity entity = repairOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repair order not found with id: " + id));
        return RepairOrderMapper.toDTO(entity);
    }

    public RepairOrderGetDTO save(RepairOrderPostDTO dto) {
        CustomerEntity customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + dto.customerId()));

        VehicleEntity vehicle = vehicleRepository.findById(dto.vehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + dto.vehicleId()));

        MechanicEntity mechanic = mechanicRepository.findById(dto.mechanicId())
                .orElseThrow(() -> new ResourceNotFoundException("Mechanic not found with id: " + dto.mechanicId()));

        VehicleStatusEntity status = vehicleStatusRepository.findById(dto.statusId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle status not found with id: " + dto.statusId()));

        UserEntity createdBy = userRepository.findById(dto.createdBy())
                .orElseThrow(() -> new ResourceNotFoundException("User (createdBy) not found with id: " + dto.createdBy()));

        RepairOrderEntity entity = RepairOrderMapper.toEntityPost(dto, customer, vehicle, mechanic, status, createdBy);
        repairOrderRepository.save(entity);
        return RepairOrderMapper.toDTO(entity);
    }

    public RepairOrderGetDTO update(Long id, RepairOrderPutDTO dto) {
        RepairOrderEntity entity = repairOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repair order not found with id: " + id));

        CustomerEntity customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + dto.customerId()));

        VehicleEntity vehicle = vehicleRepository.findById(dto.vehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + dto.vehicleId()));

        MechanicEntity mechanic = mechanicRepository.findById(dto.mechanicId())
                .orElseThrow(() -> new ResourceNotFoundException("Mechanic not found with id: " + dto.mechanicId()));

        VehicleStatusEntity status = vehicleStatusRepository.findById(dto.statusId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle status not found with id: " + dto.statusId()));

        UserEntity updatedBy = userRepository.findById(dto.updatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User (updatedBy) not found with id: " + dto.updatedBy()));

        RepairOrderMapper.toEntityPut(entity, dto, customer, vehicle, mechanic, status, updatedBy);
        repairOrderRepository.save(entity);
        return RepairOrderMapper.toDTO(entity);
    }

    public void delete(Long id) {
        RepairOrderEntity entity = repairOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repair order not found with id: " + id));
        repairOrderRepository.delete(entity);
    }
}
