package com.mikadev.autocontrolapi.paymentStatus;

import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.user.UserEntity;
import com.mikadev.autocontrolapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentStatusService {
    private final PaymentStatusRepository paymentStatusRepository;
    private final UserRepository userRepository;

    public List<PaymentStatusGetDTO> findAll() {
        List<PaymentStatusEntity> entities = paymentStatusRepository.findAll();
        List<PaymentStatusGetDTO> result = new ArrayList<>();
        for (PaymentStatusEntity entity : entities) {
            result.add(PaymentStatusMapper.toDTO(entity));
        }
        return result;
    }

    public PaymentStatusGetDTO findById(Long id) {
        PaymentStatusEntity entity = paymentStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment status not found with id: " + id));
        return PaymentStatusMapper.toDTO(entity);
    }

    public PaymentStatusGetDTO save(PaymentStatusPostDTO dto, Long createdById) {
        UserEntity createdBy = userRepository.findById(createdById)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + createdById));
        PaymentStatusEntity entity = PaymentStatusMapper.toEntityPost(dto, createdBy);
        paymentStatusRepository.save(entity);
        return PaymentStatusMapper.toDTO(entity);
    }

    public PaymentStatusGetDTO update(Long id, PaymentStatusPutDTO dto, Long updatedById) {
        PaymentStatusEntity entity = paymentStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment status not found with id: " + id));
        UserEntity updatedBy = userRepository.findById(updatedById)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + updatedById));
        PaymentStatusMapper.toEntityPut(entity, dto, updatedBy);
        paymentStatusRepository.save(entity);
        return PaymentStatusMapper.toDTO(entity);
    }

    public void delete(Long id) {
        PaymentStatusEntity entity = paymentStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment status not found with id: " + id));
        paymentStatusRepository.delete(entity);
    }
}
