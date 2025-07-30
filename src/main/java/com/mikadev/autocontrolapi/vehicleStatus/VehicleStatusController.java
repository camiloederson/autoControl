package com.mikadev.autocontrolapi.vehicleStatus;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicleStatuses")
@RequiredArgsConstructor
public class VehicleStatusController {

    private final VehicleStatusService vehicleStatusService;

    @GetMapping
    public ResponseEntity<List<VehicleStatusGetDTO>> findAll() {
        List<VehicleStatusGetDTO> result = vehicleStatusService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleStatusGetDTO> findById(@PathVariable Long id) {
        VehicleStatusGetDTO dto = vehicleStatusService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<VehicleStatusGetDTO> create(@Valid @RequestBody VehicleStatusPostDTO dto) {
        VehicleStatusGetDTO created = vehicleStatusService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleStatusGetDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody VehicleStatusPutDTO dto) {
        VehicleStatusGetDTO updated = vehicleStatusService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleStatusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}