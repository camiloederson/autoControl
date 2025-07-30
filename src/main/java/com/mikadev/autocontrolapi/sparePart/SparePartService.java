package com.mikadev.autocontrolapi.sparePart;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.sparePartBrand.SparePartBrandEntity;
import com.mikadev.autocontrolapi.sparePartBrand.SparePartBrandRepository;
import com.mikadev.autocontrolapi.sparePartCategory.SparePartCategoryEntity;
import com.mikadev.autocontrolapi.sparePartCategory.SparePartCategoryRepository;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SparePartService {
    private final SparePartRepository sparePartRepository;
    private final SparePartMapper sparePartMapper;
    private final UserRepository userRepository;
    private final SparePartBrandRepository sparePartBrandRepository;
    private final SparePartCategoryRepository sparePartCategoryRepository;

    public SparePartService(SparePartRepository sparePartRepository, SparePartMapper sparePartMapper, UserRepository userRepository,
                            SparePartBrandRepository sparePartBrandRepository, SparePartCategoryRepository sparePartCategoryRepository) {
        this.sparePartRepository = sparePartRepository;
        this.sparePartMapper = sparePartMapper;
        this.userRepository = userRepository;
        this.sparePartBrandRepository = sparePartBrandRepository;
        this.sparePartCategoryRepository = sparePartCategoryRepository;
    }

    // findAll
    public List<SparePartGetDTO> findAll(){
        List<SparePartEntity> entities = sparePartRepository.findAll();
        List<SparePartGetDTO> dtos = new ArrayList<>();
        for (SparePartEntity entity : entities) {
            dtos.add(sparePartMapper.toDTO(entity));
        }
        return dtos;
    }

    // findById
    public SparePartGetDTO findById(Long id) {
        return sparePartMapper.toDTO(sparePartRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SparePart " + id + " no found")));
    }

    // save
    public SparePartGetDTO save(SparePartPostDTO dto) {
        UserEntity createdBy = userRepository.findById(dto.createdBy()).orElseThrow(
                () -> new ResourceNotFoundException("User " + dto.createdBy() + " not found"));

        SparePartBrandEntity brand = sparePartBrandRepository.findById(dto.brandId()).orElseThrow(
                () -> new ResourceNotFoundException("SparePartBrand " + dto.brandId() + " not found"));

        SparePartCategoryEntity category = sparePartCategoryRepository.findById(dto.categoryId()).orElseThrow(
                () -> new ResourceNotFoundException("SparePartCategory " + dto.categoryId() + " not found"));

        SparePartEntity saved = sparePartRepository.save(sparePartMapper.toSaveEntity(dto, category, brand, createdBy));

        return sparePartMapper.toDTO(saved);
    }

    // update
    public SparePartGetDTO update(Long id, SparePartPutDTO dto) {
        UserEntity updatedBy = userRepository.findById(dto.updatedBy()).orElseThrow(
                () -> new ResourceNotFoundException("User " + dto.updatedBy() + " not found"));

        SparePartBrandEntity brand = sparePartBrandRepository.findById(dto.brandId()).orElseThrow(
                () -> new ResourceNotFoundException("SparePartBrand " + dto.brandId() + " not found"));

        SparePartCategoryEntity category = sparePartCategoryRepository.findById(dto.categoryId()).orElseThrow(
                () -> new ResourceNotFoundException("SparePartCategory " + dto.categoryId() + " not found"));

        SparePartEntity existing = sparePartRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SparePart " + id + " not found"));


        SparePartEntity toUpdate = sparePartMapper.toPutEntity(existing, dto, category, brand, updatedBy);
        SparePartEntity updated = sparePartRepository.save(toUpdate);
        return sparePartMapper.toDTO(toUpdate);
    }

    // delete
    public void delete(Long id) {
        if (!sparePartRepository.existsById(id)) {
            throw new ResourceNotFoundException("SparePart " + id + " not found");
        }
        sparePartRepository.deleteById(id);
    }
}
