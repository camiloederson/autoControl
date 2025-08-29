package com.mikadev.autocontrolapi.customer;

import com.mikadev.autocontrolapi.user.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomerMapper {
    public CustomerGetDTO toDTO(CustomerEntity customerEntity) {
        return new CustomerGetDTO(
                customerEntity.getId(),
                customerEntity.getName(),
                customerEntity.getSurname(),
                customerEntity.getPhone(),
                customerEntity.getEmail(),
                customerEntity.getPhone(),
                customerEntity.isActive());
    }

    public CustomerEntity toEntityPost(CustomerPostDTO customerPostDTO,
                                       UserEntity createdBy) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerPostDTO.name());
        customerEntity.setSurname(customerPostDTO.surname());
        customerEntity.setPhone(customerPostDTO.phone());
        customerEntity.setEmail(customerPostDTO.email());
        customerEntity.setDui(customerPostDTO.dui());
        customerEntity.setActive(customerPostDTO.active());
        customerEntity.setCreatedBy(createdBy);
        customerEntity.setCreatedAt(LocalDateTime.now());
        return customerEntity;
    }

    public CustomerEntity toEntityPut(CustomerUpdateDTO customerUpdateDTO,
                                      UserEntity updatedBy) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerUpdateDTO.name());
        customerEntity.setSurname(customerUpdateDTO.surname());
        customerEntity.setPhone(customerUpdateDTO.phone());
        customerEntity.setEmail(customerUpdateDTO.email());
        customerEntity.setDui(customerUpdateDTO.dui());
        customerEntity.setActive(customerUpdateDTO.active());
        customerEntity.setUpdatedBy(updatedBy);
        return customerEntity;
    }
}
