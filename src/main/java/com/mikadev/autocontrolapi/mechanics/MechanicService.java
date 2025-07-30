package com.mikadev.autocontrolapi.mechanics;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MechanicService {

    private final MechanicRepository mechanicRepository;
    private final UserRepository userRepository;
    public MechanicService(MechanicRepository mechanicRepository, UserRepository userRepository) {
        this.mechanicRepository = mechanicRepository;
        this.userRepository = userRepository;
    }

    // findById
    public MechanicGetDTO findById(Long id) {
        MechanicEntity mechanicEntity = mechanicRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Mechanic with id " + id + " not found"));

        return new MechanicGetDTO(
                mechanicEntity.getId(), mechanicEntity.getName(),
                mechanicEntity.getSurname(), mechanicEntity.getEmail(), mechanicEntity.getPhone(),
                mechanicEntity.getDateIn(), mechanicEntity.getDateOut(), mechanicEntity.isActive(), mechanicEntity.getBirthDate()
        );
    }

    // findAll
    public List<MechanicGetDTO> findAll() {
        List<MechanicEntity> mechanicEntitieList = mechanicRepository.findAll();
        List<MechanicGetDTO> mechanicGetDTOList = new ArrayList<>();
        for (MechanicEntity mechanicEntity : mechanicEntitieList) {
            mechanicGetDTOList.add(new MechanicGetDTO(
                    mechanicEntity.getId(), mechanicEntity.getName(),
                    mechanicEntity.getSurname(), mechanicEntity.getEmail(), mechanicEntity.getPhone(),
                    mechanicEntity.getDateIn(), mechanicEntity.getDateOut(), mechanicEntity.isActive(), mechanicEntity.getBirthDate()
            ));
        }
    return mechanicGetDTOList;
    }

    // save
    public MechanicGetDTO save(MechanicPostDTO mechanicPostDTO) {
        UserEntity createdBy = userRepository.findById(mechanicPostDTO.createdById()).orElseThrow(
                () -> new ResourceNotFoundException("CreatedBy user not found"));

        MechanicEntity mechanicEntity = new MechanicEntity();
        mechanicEntity.setName(mechanicPostDTO.name());
        mechanicEntity.setSurname(mechanicPostDTO.surname());
        mechanicEntity.setEmail(mechanicPostDTO.email());
        mechanicEntity.setPhone(mechanicPostDTO.phone());
        mechanicEntity.setDateIn(mechanicPostDTO.dateIn());
        mechanicEntity.setDateOut(mechanicPostDTO.dateOut());
        mechanicEntity.setActive(mechanicPostDTO.active());
        mechanicEntity.setBirthDate(mechanicPostDTO.birthDate());
        mechanicEntity.setCreatedBy(createdBy);
        mechanicEntity.setCreatedAt(LocalDateTime.now());

        MechanicEntity savedEntity = mechanicRepository.save(mechanicEntity);

        return new MechanicGetDTO(
                mechanicEntity.getId(), mechanicEntity.getName(),
                mechanicEntity.getSurname(), mechanicEntity.getEmail(),
                mechanicEntity.getPhone(), mechanicEntity.getDateIn(), mechanicEntity.getDateOut(),
                mechanicEntity.isActive(), mechanicEntity.getBirthDate());
    }

    // update
    public MechanicGetDTO update(Long id, MechanicUpdateDTO mechanicUpdateDTO) {
        MechanicEntity mechanicEntity = mechanicRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Mechanic with id " + id + " not found"));

        UserEntity updatedBy = userRepository.findById(mechanicUpdateDTO.updatedById()).orElseThrow(
                () -> new ResourceNotFoundException("UpdatedBy user not found"));

        mechanicEntity.setName(mechanicUpdateDTO.name());
        mechanicEntity.setSurname(mechanicUpdateDTO.surname());
        mechanicEntity.setEmail(mechanicUpdateDTO.email());
        mechanicEntity.setPhone(mechanicUpdateDTO.phone());
        mechanicEntity.setDateIn(mechanicUpdateDTO.dateIn());
        mechanicEntity.setDateOut(mechanicUpdateDTO.dateOut());
        mechanicEntity.setActive(mechanicUpdateDTO.active());
        mechanicEntity.setBirthDate(mechanicUpdateDTO.birthDate());
        mechanicEntity.setUpdatedBy(updatedBy);
        mechanicEntity.setUpdatedAt(LocalDateTime.now());

        MechanicEntity updatedEntity = mechanicRepository.save(mechanicEntity);

        return new MechanicGetDTO(
                mechanicEntity.getId(), mechanicEntity.getName(),
                mechanicEntity.getSurname(), mechanicEntity.getEmail(),
                mechanicEntity.getPhone(), mechanicEntity.getDateIn(), mechanicEntity.getDateOut(),
                mechanicEntity.isActive(), mechanicEntity.getBirthDate());
    }

    // delete
    public void delete(Long id) {
        if (!mechanicRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mechanic with id " + id + " not found");
        }
        mechanicRepository.deleteById(id);
    }
}