package com.mikadev.autocontrolapi.sparePartUsed;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.repairOrder.RepairOrderEntity;
import com.mikadev.autocontrolapi.repairOrder.RepairOrderRepository;
import com.mikadev.autocontrolapi.sparePart.SparePartEntity;
import com.mikadev.autocontrolapi.sparePart.SparePartRepository;
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
public class SparePartUsedService {

    private final SparePartUsedRepository sparePartUsedRepository;
    private final RepairOrderRepository repairOrderRepository;
    private final SparePartRepository sparePartRepository;
    private final UserRepository userRepository;

    // findAll
    public List<SparePartUsedGetDTO> findAll() {
        List<SparePartUsedEntity> entities = sparePartUsedRepository.findAll();
        List<SparePartUsedGetDTO> dtos = new ArrayList<>();
        for (SparePartUsedEntity entity : entities) {
            dtos.add(SparePartUsedMapper.toDTO(entity));
        }
        return dtos;
    }

    // findById
    public SparePartUsedGetDTO findById(Long id) {
        SparePartUsedEntity entity = sparePartUsedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SparePartUsed not found with id " + id));
        return SparePartUsedMapper.toDTO(entity);
    }

    // save
    public SparePartUsedGetDTO save(SparePartUsedPostDTO dto) {
        RepairOrderEntity repairOrder = repairOrderRepository.findById(dto.repairOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("RepairOrder not found with id " + dto.repairOrderId()));

        SparePartEntity sparePart = sparePartRepository.findById(dto.sparePartId())
                .orElseThrow(() -> new ResourceNotFoundException("SparePart not found with id " + dto.sparePartId()));

        UserEntity createdBy = userRepository.findById(dto.createdById())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.createdById()));

        SparePartUsedEntity entity = SparePartUsedMapper.toEntityPost(dto, repairOrder, sparePart, createdBy);
        sparePartUsedRepository.save(entity);
        return SparePartUsedMapper.toDTO(entity);
    }

    // update
    public SparePartUsedGetDTO update(Long id, SparePartUsedPutDTO dto) {
        SparePartUsedEntity entity = sparePartUsedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SparePartUsed not found with id " + id));

        RepairOrderEntity repairOrder = repairOrderRepository.findById(dto.repairOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("RepairOrder not found with id " + dto.repairOrderId()));

        SparePartEntity sparePart = sparePartRepository.findById(dto.sparePartId())
                .orElseThrow(() -> new ResourceNotFoundException("SparePart not found with id " + dto.sparePartId()));

        UserEntity updatedBy = userRepository.findById(dto.updatedById())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.updatedById()));

        SparePartUsedMapper.toEntityPut(entity, dto, repairOrder, sparePart, updatedBy);
        sparePartUsedRepository.save(entity);
        return SparePartUsedMapper.toDTO(entity);
    }

    // delete
    public void delete(Long id) {
        SparePartUsedEntity entity = sparePartUsedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SparePartUsed not found with id " + id));
        sparePartUsedRepository.delete(entity);
    }
}