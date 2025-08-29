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
    private final CustomerMapper customerMapper;

    public CustomerService( CustomerRepository customerRepository,
                            UserRepository userRepository,
                            CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.customerMapper = customerMapper;
    }

    // findById
    public CustomerGetDTO findById(Long id){
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer does not exist on database"));
        return customerMapper.toDTO(customerEntity);
    }

    // findAll
    public List<CustomerGetDTO> findAll(){
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        return customerEntityList.stream().map(
                customerMapper::toDTO).toList();
    }

    // save
    public CustomerGetDTO save(CustomerPostDTO customerPostDTO){
        UserEntity createdBy = userRepository.findById(customerPostDTO.createdById()).orElseThrow(
                () -> new ResourceNotFoundException("CreatedBy user does not exist"));
        CustomerEntity customerToSave = customerMapper.toEntityPost(customerPostDTO, createdBy);
        CustomerEntity customerSaved = customerRepository.save(customerToSave);

        return customerMapper.toDTO(customerSaved);
    }

    // update
    public CustomerGetDTO update(CustomerUpdateDTO customerUpdateDTO, Long id){
        CustomerEntity customerEntityToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer does not exist. Id incorrect"));

        UserEntity updatedBy = userRepository.findById(customerUpdateDTO.updatedById()).orElseThrow(
                () -> new ResourceNotFoundException("UpdatedBy user does not exist. Id is incorrect"));

        CustomerEntity customerUpdated = customerRepository.save(customerMapper.toEntityPut(customerUpdateDTO, updatedBy));

        return customerMapper.toDTO(customerUpdated);
    }

    // deleteById
    public void deleteById(Long id){
       CustomerEntity customerToDelete = customerRepository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("Customer id does not exist"));
        customerRepository.delete(customerToDelete);
    }
}
