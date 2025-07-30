package com.mikadev.autocontrolapi.repairOrder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repair-orders")
@RequiredArgsConstructor
public class RepairOrderController {

    private final RepairOrderService repairOrderService;

    @GetMapping
    public ResponseEntity<List<RepairOrderGetDTO>> findAll() {
        return ResponseEntity.ok(repairOrderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairOrderGetDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(repairOrderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RepairOrderGetDTO> save(@RequestBody @Valid RepairOrderPostDTO dto) {
        return ResponseEntity.ok(repairOrderService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepairOrderGetDTO> update(@PathVariable Long id,
                                                    @RequestBody @Valid RepairOrderPutDTO dto) {
        return ResponseEntity.ok(repairOrderService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repairOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}