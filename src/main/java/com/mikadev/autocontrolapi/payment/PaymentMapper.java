package com.mikadev.autocontrolapi.payment;

import com.mikadev.autocontrolapi.paymentStatus.PaymentStatusEntity;
import com.mikadev.autocontrolapi.paymentType.PaymentTypeEntity;
import com.mikadev.autocontrolapi.repairOrder.RepairOrderEntity;
import com.mikadev.autocontrolapi.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public static PaymentGetDTO toDTO(PaymentEntity payment) {
        return new PaymentGetDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getRepairOrder().getId(),
                payment.getDate(),
                payment.getPaymentStatus() != null ? payment.getPaymentStatus().getId() : null,
                payment.getPaymentType().getId(),
                payment.getCreatedBy().getId(),
                payment.getUpdatedBy() != null ? payment.getUpdatedBy().getId() : null,
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }

    public static PaymentEntity toEntityPost(PaymentPostDTO dto, RepairOrderEntity repairOrder, PaymentStatusEntity status, PaymentTypeEntity type, UserEntity createdBy) {
        PaymentEntity payment = new PaymentEntity();
        payment.setAmount(dto.amount());
        payment.setRepairOrder(repairOrder);
        payment.setDate(dto.date());
        payment.setPaymentStatus(status);
        payment.setPaymentType(type);
        payment.setCreatedBy(createdBy);
        payment.setCreatedAt(java.time.LocalDateTime.now());
        return payment;
    }

    public static void toEntityPut(PaymentEntity payment, PaymentPutDTO dto, RepairOrderEntity repairOrder, PaymentStatusEntity status, PaymentTypeEntity type, UserEntity updatedBy) {
        payment.setAmount(dto.amount());
        payment.setRepairOrder(repairOrder);
        payment.setDate(dto.date());
        payment.setPaymentStatus(status);
        payment.setPaymentType(type);
        payment.setUpdatedBy(updatedBy);
        payment.setUpdatedAt(java.time.LocalDateTime.now());
    }
}
