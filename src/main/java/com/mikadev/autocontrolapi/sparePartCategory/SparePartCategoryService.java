package com.mikadev.autocontrolapi.sparePartCategory;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SparePartCategoryService {
    private final SparePartCategoryRepository sparePartCategoryRepository;
    private final SparePartCategoryMapper mapper;
    private final UserRepository userRepository;

    public SparePartCategoryService(SparePartCategoryRepository sparePartCategoryRepository, SparePartCategoryMapper mapper, UserRepository userRepository) {
        this.sparePartCategoryRepository = sparePartCategoryRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    // findAll
    public List<SparePartCategoryGetDTO> findAll(){
        List<SparePartCategoryEntity> sparePartCategoryEntities = sparePartCategoryRepository.findAll();
        List<SparePartCategoryGetDTO> sparePartCategoryGetDTOS = new ArrayList<>();
        for (SparePartCategoryEntity sparePartCategoryEntity : sparePartCategoryEntities){
            sparePartCategoryGetDTOS.add(mapper.toDTO(sparePartCategoryEntity));
        }
        return sparePartCategoryGetDTOS;
    }

    // findById
    public SparePartCategoryGetDTO findById(Long id){
        SparePartCategoryEntity sparePartCategoryEntity = sparePartCategoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SparePartCategory not found with id: " + id));
    return mapper.toDTO(sparePartCategoryEntity);
    }

    // save
    public SparePartCategoryGetDTO save(SparePartCategoryPostDTO sparePartCategoryPostDTO){
        UserEntity createdBy = userRepository.findById(sparePartCategoryPostDTO.createdBy()).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + sparePartCategoryPostDTO.createdBy()));

        SparePartCategoryEntity sparePartCategoryEntitySaved = mapper.toEntity(sparePartCategoryPostDTO, createdBy);
        return mapper.toDTO(sparePartCategoryRepository.save(sparePartCategoryEntitySaved));
    }

    // update
    public SparePartCategoryGetDTO update(Long id, SparePartCategoryPutDTO sparePartCategoryPutDTO){
        UserEntity updatedBy = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + id));

        SparePartCategoryEntity sparePartCategoryEntityUpdated = mapper.updateEntity(sparePartCategoryPutDTO, updatedBy);
        return mapper.toDTO(sparePartCategoryEntityUpdated);
    }

    // delete
    public void delete(Long id){
        if (!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        sparePartCategoryRepository.deleteById(id);
    }
}

