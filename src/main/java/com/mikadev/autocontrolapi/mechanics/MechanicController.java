package com.mikadev.autocontrolapi.mechanics;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/mechanics")
@CrossOrigin(origins = "http://localhost:4200")
public class MechanicController {
    private final MechanicService mechanicService;

    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    // findAll
    @GetMapping
    public ResponseEntity<List<MechanicGetDTO>> findAll() {
        return ResponseEntity.ok(mechanicService.findAll());
    }

    // findById
    @GetMapping("/{id}")
    public ResponseEntity<MechanicGetDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mechanicService.findById(id));
    }

    // save
    @PostMapping
    public ResponseEntity<MechanicGetDTO> save(@Valid @RequestBody MechanicPostDTO mechanicPostDTO) {
        return new ResponseEntity<>(mechanicService.save(mechanicPostDTO), HttpStatus.CREATED);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<MechanicGetDTO> update(@Valid @RequestBody MechanicUpdateDTO mechanicUpdateDTO, @PathVariable Long id) {
        return new ResponseEntity<>(mechanicService.update(id, mechanicUpdateDTO), HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        mechanicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
