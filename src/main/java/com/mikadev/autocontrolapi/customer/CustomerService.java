package com.mikadev.autocontrolapi.customer;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public CustomerService( CustomerRepository customerRepository,
                            UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    // findById
    public CustomerGetDTO findById(Long id){
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer does not exist on database"));
        return new CustomerGetDTO(
                customerEntity.getId(),
                customerEntity.getName(),
                customerEntity.getSurname(),
                customerEntity.getDui(),
                customerEntity.getEmail(),
                customerEntity.getPhone()
        );
    }

    // findAll
    public List<CustomerGetDTO> findAll(){
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        return customerEntityList.stream().map(
                 customerEntity -> new CustomerGetDTO(
                         customerEntity.getId(), customerEntity.getName(), customerEntity.getSurname(),
                        customerEntity.getDui(), customerEntity.getEmail(),
                        customerEntity.getPhone()
                )).toList();
    }

    // save
    public CustomerGetDTO save(CustomerPostDTO customerPostDTO){
        CustomerEntity customerToSave = new CustomerEntity();

        UserEntity createdBy = userRepository.findById(customerPostDTO.createdById()).orElseThrow(
                () -> new ResourceNotFoundException("CreatedBy user does not exist"));

        customerToSave.setName(customerPostDTO.name());
        customerToSave.setSurname(customerPostDTO.surname());
        customerToSave.setDui(customerPostDTO.dui());
        customerToSave.setEmail(customerPostDTO.email());
        customerToSave.setPhone(customerPostDTO.phone());
        customerToSave.setCreatedBy(createdBy);
        customerToSave.setCreatedAt(LocalDateTime.now());

        CustomerEntity customerSaved = customerRepository.save(customerToSave);

        return new CustomerGetDTO(
                customerSaved.getId(),
                customerSaved.getName(), customerSaved.getSurname(),
                customerSaved.getDui(), customerSaved.getEmail(),
                customerSaved.getPhone());
    }

    // update
    public CustomerGetDTO update(CustomerUpdateDTO customerUpdateDTO, Long id){
        CustomerEntity customerEntityToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer does not exist. Id incorrect"));

        UserEntity updatedBy = userRepository.findById(customerUpdateDTO.updatedById()).orElseThrow(
                () -> new ResourceNotFoundException("UpdatedBy user does not exist. Id is incorrect"));

        customerEntityToUpdate.setName(customerUpdateDTO.name());
        customerEntityToUpdate.setSurname(customerUpdateDTO.surname());
        customerEntityToUpdate.setDui(customerUpdateDTO.dui());
        customerEntityToUpdate.setEmail(customerUpdateDTO.email());
        customerEntityToUpdate.setPhone(customerUpdateDTO.phone());
        customerEntityToUpdate.setUpdatedBy(updatedBy);

        CustomerEntity customerUpdated = customerRepository.save(customerEntityToUpdate);

        return new CustomerGetDTO(
                customerUpdated.getId(),
                customerUpdated.getName(), customerUpdateDTO.surname(),
                customerUpdated.getDui(), customerUpdated.getEmail(),
                customerUpdated.getPhone());
    }

    // deleteById
    public void deleteById(Long id){
       CustomerEntity customerToDelete = customerRepository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("Customer id does not exist"));
        customerRepository.delete(customerToDelete);
    }
}
