package com.mikadev.autocontrolapi.user;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.role.RoleEntity;
import com.mikadev.autocontrolapi.role.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public List<UserGetDTO> findAll() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserGetDTO> userGetDTOList = new ArrayList<>();

        for (UserEntity userEntity : userEntityList){
            userGetDTOList.add(new UserGetDTO(
                    userEntity.getId(),
                    userEntity.getName(),
                    userEntity.getSurname(),
                    userEntity.getEmail(),
                    userEntity.getUserName(),
                    new RoleNameDTO(userEntity.getRole().getName())
            ));
        }
        return userGetDTOList;
    }


    public UserGetDTO findById(Long id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User does not exist in database")
        );

        return new UserGetDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getEmail(),
                userEntity.getUserName(),
                new RoleNameDTO(userEntity.getRole().getName()));
    }


    public UserGetDTO save(UserPostDTO userPostDTO) {
       UserEntity userEntity = new UserEntity();

       RoleEntity roleEntity = roleRepository.findById(userPostDTO.roleId()).orElseThrow(
               () -> new ResourceNotFoundException("Role was not founded")
       );


       UserEntity createdBy = userRepository.findById(userPostDTO.createdById()).orElseThrow(
               () -> new ResourceNotFoundException("CreatedBy User was not found")
       );

       userEntity.setCreatedAt(LocalDateTime.now());
       userEntity.setEmail(userPostDTO.email());
       userEntity.setPassword(userPostDTO.password());
       userEntity.setName(userPostDTO.name());
       userEntity.setSurname(userPostDTO.surname());
       userEntity.setUserName(userPostDTO.userName());
       userEntity.setRole(roleEntity);
       userEntity.setCreatedBy(createdBy);
       UserEntity userEntitySaved = userRepository.save(userEntity);

       return new UserGetDTO(
               userEntitySaved.getId(),
               userEntitySaved.getName(),
               userEntitySaved.getSurname(),
               userEntitySaved.getEmail(),
               userEntitySaved.getUserName(),
               new RoleNameDTO(roleEntity.getName())
       );

    }


    public UserGetDTO update(UserUpdateDTO userUpdateDTO, Long id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User does not exist in database"));
        UserEntity updatedBy = userRepository.findById(userUpdateDTO.updatedById()).orElseThrow(
                () -> new ResourceNotFoundException("UpdatedBy User does not exist"));
        RoleEntity newRole = roleRepository.findById(userUpdateDTO.roleId()).orElseThrow(
                () -> new ResourceNotFoundException("Role was not found"));

        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setUpdatedBy(updatedBy);
        userEntity.setPassword(userUpdateDTO.password());
        userEntity.setName(userUpdateDTO.name());
        userEntity.setSurname(userUpdateDTO.surname());
        userEntity.setEmail(userUpdateDTO.email());
        userEntity.setUserName(userUpdateDTO.userName());
        userEntity.setRole(newRole);
        UserEntity userEntityUpdated = userRepository.save(userEntity);

        return new UserGetDTO(
                userEntityUpdated.getId(),
                userEntityUpdated.getName(),
                userEntityUpdated.getSurname(),
                userEntityUpdated.getEmail(),
                userEntityUpdated.getUserName(),
                new RoleNameDTO(userEntityUpdated.getRole().getName())
        );
    }

    public void deleteById(Long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User to delete was not found"));
        userRepository.deleteById(id);
    }
}
