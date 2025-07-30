package com.mikadev.autocontrolapi.paymentType;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment-types")
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    // findAll
    @GetMapping
    public ResponseEntity<List<PaymentTypeGetDTO>> findAll() {
        List<PaymentTypeGetDTO> dtos = paymentTypeService.findAll();
        return ResponseEntity.ok(dtos);
    }

    // findById
    @GetMapping("/{id}")
    public ResponseEntity<PaymentTypeGetDTO> findById(@PathVariable Long id) {
        PaymentTypeGetDTO dto = paymentTypeService.findById(id);
        return ResponseEntity.ok(dto);
    }

    // save
    @PostMapping
    public ResponseEntity<PaymentTypeGetDTO> save(@RequestBody @Valid PaymentTypePostDTO dto,
                                                  @RequestParam Long userId) {
        PaymentTypeGetDTO saved = paymentTypeService.save(dto, userId);
        return ResponseEntity.ok(saved);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<PaymentTypeGetDTO> update(@PathVariable Long id,
                                                    @RequestBody @Valid PaymentTypePutDTO dto,
                                                    @RequestParam Long userId) {
        PaymentTypeGetDTO updated = paymentTypeService.update(id, dto, userId);
        return ResponseEntity.ok(updated);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}