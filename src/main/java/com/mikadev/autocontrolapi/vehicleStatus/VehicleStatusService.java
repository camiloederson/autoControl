package com.mikadev.autocontrolapi.vehicleStatus;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleStatusService {

    private final VehicleStatusRepository vehicleStatusRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<VehicleStatusGetDTO> findAll() {
        List<VehicleStatusEntity> entities = vehicleStatusRepository.findAll();
        List<VehicleStatusGetDTO> dtos = new ArrayList<>();
        for (VehicleStatusEntity entity : entities) {
            dtos.add(VehicleStatusMapper.toGetDTO(entity));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public VehicleStatusGetDTO findById(Long id) {
        VehicleStatusEntity entity = vehicleStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleStatus not found with ID: " + id));
        return VehicleStatusMapper.toGetDTO(entity);
    }

    public VehicleStatusGetDTO create(VehicleStatusPostDTO dto) {
        UserEntity createdBy = userRepository.findById(dto.createdBy())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.createdBy()));

        VehicleStatusEntity entity = VehicleStatusMapper.toEntity(dto, createdBy);
        vehicleStatusRepository.save(entity);

        return VehicleStatusMapper.toGetDTO(entity);
    }

    public VehicleStatusGetDTO update(Long id, VehicleStatusPutDTO dto) {
        VehicleStatusEntity entity = vehicleStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleStatus not found with ID: " + id));

        UserEntity updatedBy = userRepository.findById(dto.updatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.updatedBy()));

        VehicleStatusMapper.updateEntity(entity, dto, updatedBy);

        return VehicleStatusMapper.toGetDTO(entity);
    }

    public void delete(Long id) {
        if (!vehicleStatusRepository.existsById(id)) {
            throw new ResourceNotFoundException("VehicleStatus not found with ID: " + id);
        }
        vehicleStatusRepository.deleteById(id);
    }
}