package com.mikadev.autocontrolapi.role;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository,
                       UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    // findAll

    public List<RoleGetDTO> findAll (){
        return roleRepository.findAll().stream()
                .map((roleEntity) -> new RoleGetDTO(
                        roleEntity.getId(), roleEntity.getName(),
                        roleEntity.getDescription()
                ))
                .toList();
    }

    // findById

    public RoleGetDTO findById(Long id){
        return roleRepository.findById(id).map(
                roleEntity -> new RoleGetDTO(
                        roleEntity.getId(),
                        roleEntity.getName(),
                        roleEntity.getDescription()
                )
        ).orElseThrow( () -> new ResourceNotFoundException("Role does not exist"));
    }

    // create
    public RoleGetDTO save(RolePostDTO rolePostDTO){
        UserEntity createdBy = userRepository.findById(rolePostDTO.createdById())
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(rolePostDTO.name());
        roleEntity.setDescription(rolePostDTO.description());
        roleEntity.setCreatedAt(LocalDateTime.now());
        roleEntity.setCreatedBy(createdBy);
        RoleEntity roleEntitySaved = roleRepository.save(roleEntity);

        return new RoleGetDTO(
                roleEntitySaved.getId(),
                roleEntitySaved.getName(),
                roleEntitySaved.getDescription()
        );
    }

    // update
    public RoleGetDTO update (Long id, RoleUpdateDTO roleUpdateDTO){
        RoleEntity roleEntityToUpdate = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role does not exist"));

        UserEntity updatedBy = userRepository.findById(roleUpdateDTO.updatedById())
                .orElseThrow(() -> new ResourceNotFoundException("User does not"));
        roleEntityToUpdate.setName(roleUpdateDTO.name());
        roleEntityToUpdate.setDescription(roleUpdateDTO.description());
        roleEntityToUpdate.setUpdatedAt(LocalDateTime.now());
        roleEntityToUpdate.setUpdatedBy(updatedBy);
        RoleEntity roleEntityUpdated = roleRepository.save(roleEntityToUpdate);

        return new RoleGetDTO(
                roleEntityToUpdate.getId(),
                roleEntityToUpdate.getName(),
                roleEntityToUpdate.getDescription()
        );
    }

    // delete

    public void delete (Long id){
        roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Role doesn't exist"));
        roleRepository.deleteById(id);
    }
}
