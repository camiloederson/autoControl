package com.mikadev.autocontrolapi.paymentStatus;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-statuses")
@RequiredArgsConstructor
public class PaymentStatusController {

    private final PaymentStatusService paymentStatusService;

    @GetMapping
    public ResponseEntity<List<PaymentStatusGetDTO>> findAll() {
        return ResponseEntity.ok(paymentStatusService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentStatusGetDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentStatusService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentStatusGetDTO> save(
            @RequestBody @Valid PaymentStatusPostDTO dto,
            @RequestParam Long createdById
    ) {
        return ResponseEntity.ok(paymentStatusService.save(dto, createdById));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentStatusGetDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid PaymentStatusPutDTO dto,
            @RequestParam Long updatedById
    ) {
        return ResponseEntity.ok(paymentStatusService.update(id, dto, updatedById));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentStatusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}