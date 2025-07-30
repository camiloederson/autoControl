package com.mikadev.autocontrolapi.vehicleBrand;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleBrandService {
    private final VehicleBrandRepository vehicleBrandRepository;

    // findById
    public VehicleBrandService(VehicleBrandRepository vehicleBrandRepository) {
        this.vehicleBrandRepository = vehicleBrandRepository;
    }

    public VehicleBrandGetDTO findById(Long id){
        VehicleBrandEntity vehicleBrandEntity = vehicleBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand does not exist"));

        return new VehicleBrandGetDTO(
                vehicleBrandEntity.getId(), vehicleBrandEntity.getName()
        );
    }

    //  findAll
    public List<VehicleBrandGetDTO> findAll(){
        List<VehicleBrandEntity> vehicleBrandEntities = vehicleBrandRepository.findAll();

        return vehicleBrandEntities.stream()
                .map(v -> new VehicleBrandGetDTO(
                                v.getId(), v.getName()
                )).toList();
    }

    // save
    public VehicleBrandGetDTO save(VehicleBrandPostDTO vehicleBrand){
        VehicleBrandEntity vehicleBrandEntity = new VehicleBrandEntity();

        vehicleBrandEntity.setName(vehicleBrand.name());
        VehicleBrandEntity vehicleBrandEntitySaved =
                vehicleBrandRepository.save(vehicleBrandEntity);

        return new VehicleBrandGetDTO(
                vehicleBrandEntitySaved.getId(), vehicleBrandEntitySaved.getName()
        );
    }

    // update
    public VehicleBrandGetDTO update(Long id, VehicleBrandPostDTO vehicleBrand){
        VehicleBrandEntity vehicleBrandEntityToUpdate = vehicleBrandRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Brand does not exist"));

        vehicleBrandEntityToUpdate.setName(vehicleBrand.name());
        VehicleBrandEntity vehicleBrandEntitySaved =
                vehicleBrandRepository.save(vehicleBrandEntityToUpdate);

        return new VehicleBrandGetDTO(
                vehicleBrandEntitySaved.getId(), vehicleBrandEntitySaved.getName()
        );
    }

    // delete
    public void delete(Long id){
        vehicleBrandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Brand does not exist")
        );
        vehicleBrandRepository.deleteById(id);
    }
}
