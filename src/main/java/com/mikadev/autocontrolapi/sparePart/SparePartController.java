package com.mikadev.autocontrolapi.sparePart;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/spareParts")
public class SparePartController {
    private final SparePartService sparePartService;

    public SparePartController(SparePartService sparePartService) {
        this.sparePartService = sparePartService;
    }

    // findAll
    @GetMapping
    public ResponseEntity<List<SparePartGetDTO>> findAll() {
        return new ResponseEntity<>(sparePartService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SparePartGetDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(sparePartService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SparePartGetDTO> save(@Valid @RequestBody SparePartPostDTO dto) {
        return new ResponseEntity<>(sparePartService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SparePartGetDTO> update(@PathVariable Long id, @Valid @RequestBody SparePartPutDTO dto) {
        return new ResponseEntity<>(sparePartService.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sparePartService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
