package com.mikadev.autocontrolapi.payment;

import com.mikadev.autocontrolapi.paymentStatus.PaymentStatusEntity;
import com.mikadev.autocontrolapi.paymentStatus.PaymentStatusRepository;
import com.mikadev.autocontrolapi.exceptions.ResourceNotFoundException;
import com.mikadev.autocontrolapi.paymentType.PaymentTypeEntity;
import com.mikadev.autocontrolapi.paymentType.PaymentTypeRepository;
import com.mikadev.autocontrolapi.repairOrder.RepairOrderEntity;
import com.mikadev.autocontrolapi.repairOrder.RepairOrderRepository;
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
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RepairOrderRepository repairOrderRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final UserRepository userRepository;
    private final PaymentMapper paymentMapper;

    @Transactional(readOnly = true)
    public List<PaymentGetDTO> findAll() {
        List<PaymentEntity> entities = paymentRepository.findAll();
        List<PaymentGetDTO> result = new ArrayList<>();

        for (PaymentEntity entity : entities) {
            result.add(PaymentMapper.toDTO(entity));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public PaymentGetDTO findById(Long id) {
        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment with ID " + id + " not found"));

        return PaymentMapper.toDTO(entity);
    }

    public void save(PaymentPostDTO dto) {
        RepairOrderEntity repairOrder = repairOrderRepository.findById(dto.repairOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("RepairOrder with ID " + dto.repairOrderId() + " not found"));

        PaymentStatusEntity status = null;
        if (dto.paymentStatusId() != null) {
            status = paymentStatusRepository.findById(dto.paymentStatusId())
                    .orElseThrow(() -> new ResourceNotFoundException("PaymentStatus with ID " + dto.paymentStatusId() + " not found"));
        }

        PaymentTypeEntity type = paymentTypeRepository.findById(dto.paymentTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("PaymentType with ID " + dto.paymentTypeId() + " not found"));

        UserEntity createdBy = userRepository.findById(dto.createdBy())
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + dto.createdBy() + " not found"));

        PaymentEntity entity = PaymentMapper.toEntityPost(dto, repairOrder, status, type, createdBy);
        paymentRepository.save(entity);
    }

    public void update(Long id, PaymentPutDTO dto) {
        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment with ID " + id + " not found"));

        RepairOrderEntity repairOrder = repairOrderRepository.findById(dto.repairOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("RepairOrder with ID " + dto.repairOrderId() + " not found"));

        PaymentStatusEntity status = null;
        if (dto.paymentStatusId() != null) {
            status = paymentStatusRepository.findById(dto.paymentStatusId())
                    .orElseThrow(() -> new ResourceNotFoundException("PaymentStatus with ID " + dto.paymentStatusId() + " not found"));
        }

        PaymentTypeEntity type = paymentTypeRepository.findById(dto.paymentTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("PaymentType with ID " + dto.paymentTypeId() + " not found"));

        UserEntity updatedBy = userRepository.findById(dto.updatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + dto.updatedBy() + " not found"));

        PaymentMapper.toEntityPut(entity, dto, repairOrder, status, type, updatedBy);
        paymentRepository.save(entity);
    }

    public void delete(Long id) {
        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment with ID " + id + " not found"));

        paymentRepository.delete(entity);
    }
}