package com.mikadev.autocontrolapi.paymentType;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentTypeService {
    private final PaymentTypeRepository paymentTypeRepository;
    private final UserRepository userRepository;

    // findAll
    public List<PaymentTypeGetDTO> findAll() {
        List<PaymentTypeEntity> entities = paymentTypeRepository.findAll();
        List<PaymentTypeGetDTO> dtos = new ArrayList<>();
        for (PaymentTypeEntity entity : entities) {
            dtos.add(PaymentTypeMapper.toDTO(entity));
        }
        return dtos;
    }

    // findById
    public PaymentTypeGetDTO findById(Long id) {
        PaymentTypeEntity entity = paymentTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentType not found with id " + id));
        return PaymentTypeMapper.toDTO(entity);
    }

    // save
    public PaymentTypeGetDTO save(PaymentTypePostDTO dto, Long userId) {
        UserEntity createdBy = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        PaymentTypeEntity entity = PaymentTypeMapper.toEntityPost(dto, createdBy);
        paymentTypeRepository.save(entity);
        return PaymentTypeMapper.toDTO(entity);
    }

    // update
    public PaymentTypeGetDTO update(Long id, PaymentTypePutDTO dto, Long userId) {
        PaymentTypeEntity entity = paymentTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentType not found with id " + id));

        UserEntity updatedBy = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        PaymentTypeMapper.toEntityPut(entity, dto, updatedBy);
        paymentTypeRepository.save(entity);
        return PaymentTypeMapper.toDTO(entity);
    }

    // delete
    public void delete(Long id) {
        PaymentTypeEntity entity = paymentTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentType not found with id " + id));
        paymentTypeRepository.delete(entity);
    }
}
