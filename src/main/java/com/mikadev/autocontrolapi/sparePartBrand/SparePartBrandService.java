package com.mikadev.autocontrolapi.sparePartBrand;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SparePartBrandService {

    private final SparePartBrandRepository sparePartBrandRepository;
    private final SparePartBrandMapper sparePartBrandMapper;
    private final UserRepository userRepository;

    public SparePartBrandService(SparePartBrandRepository sparePartBrandRepository,
                                 SparePartBrandMapper sparePartBrandMapper, UserRepository userRepository) {
        this.sparePartBrandRepository = sparePartBrandRepository;
        this.sparePartBrandMapper = sparePartBrandMapper;
        this.userRepository = userRepository;
    }

    // findById
    @Transactional(readOnly = true)
    public SparePartBrandGetDTO findById(Long id){
        SparePartBrandEntity sparePartBrandEntity = sparePartBrandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Spare Part Brand Not Found"));
        return sparePartBrandMapper.toDTO(sparePartBrandEntity);
    }

    // findAll
    @Transactional(readOnly = true)
    public List<SparePartBrandGetDTO> findAll(){
        List<SparePartBrandEntity> sparePartBrandEntitieList = sparePartBrandRepository.findAll();
        List<SparePartBrandGetDTO> sparePartBrandGetDTOList = new ArrayList<>();
        for (SparePartBrandEntity sparePartBrandEntity : sparePartBrandEntitieList) {
            sparePartBrandGetDTOList.add(
                    sparePartBrandMapper.toDTO(sparePartBrandEntity));
        }
        return sparePartBrandGetDTOList;
    }

    // save
    @Transactional
    public SparePartBrandGetDTO save(SparePartBrandPostDTO sparePartBrandPostDTO){
        SparePartBrandEntity sparePartBrandEntity = new SparePartBrandEntity();
        UserEntity createdBy = userRepository.findById(sparePartBrandPostDTO.createdBy()).orElseThrow(
                () -> new ResourceNotFoundException("User Not Found"));

        sparePartBrandEntity.setName(sparePartBrandPostDTO.name());
        sparePartBrandEntity.setDescription(sparePartBrandPostDTO.description());
        sparePartBrandEntity.setCreatedBy(createdBy);
        sparePartBrandEntity.setCreatedAt(LocalDateTime.now());

        SparePartBrandEntity savedSparePartBrandEntity = sparePartBrandRepository.save(sparePartBrandEntity);
        return sparePartBrandMapper.toDTO(savedSparePartBrandEntity);
    }

    // update
    @Transactional
    public SparePartBrandGetDTO update(Long id, SparePartBrandPutDTO sparePartBrandPutDTO){
        SparePartBrandEntity sparePartBrandEntity = sparePartBrandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Spare Part Brand Not Found"));

        UserEntity updatedBy = userRepository.findById(sparePartBrandPutDTO.updatedBy()).orElseThrow(
                () -> new ResourceNotFoundException("User Not Found"));
        sparePartBrandEntity.setName(sparePartBrandPutDTO.name());
        sparePartBrandEntity.setDescription(sparePartBrandPutDTO.description());
        sparePartBrandEntity.setUpdatedBy(updatedBy);
        sparePartBrandEntity.setUpdatedAt(LocalDateTime.now());

        SparePartBrandEntity updatedSparePartBrandEntity = sparePartBrandRepository.save(sparePartBrandEntity);
        return sparePartBrandMapper.toDTO(updatedSparePartBrandEntity);
    }

    // delete
    @Transactional
    public void delete(Long id){
        if(!sparePartBrandRepository.existsById(id)){
            throw new ResourceNotFoundException("Spare Part Brand Not Found");
        }
        sparePartBrandRepository.deleteById(id);
    }
}


