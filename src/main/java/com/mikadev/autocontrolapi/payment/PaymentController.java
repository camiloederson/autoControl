package com.mikadev.autocontrolapi.payment;

import com.mikadev.autocontrolapi.payment.PaymentGetDTO;
import com.mikadev.autocontrolapi.payment.PaymentPostDTO;
import com.mikadev.autocontrolapi.payment.PaymentPutDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentGetDTO>> findAll() {
        List<PaymentGetDTO> payments = paymentService.findAll();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentGetDTO> findById(@PathVariable Long id) {
        PaymentGetDTO payment = paymentService.findById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PaymentPostDTO dto) {
        paymentService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody PaymentPutDTO dto) {
        paymentService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}