package com.mikadev.autocontrolapi.sparePartUsed;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spare-parts-used")
@RequiredArgsConstructor
public class SparePartUsedController {

    private final SparePartUsedService sparePartUsedService;

    // findAll
    @GetMapping
    public ResponseEntity<List<SparePartUsedGetDTO>> findAll() {
        List<SparePartUsedGetDTO> list = sparePartUsedService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // findById
    @GetMapping("/{id}")
    public ResponseEntity<SparePartUsedGetDTO> findById(@PathVariable Long id) {
        SparePartUsedGetDTO dto = sparePartUsedService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // save
    @PostMapping
    public ResponseEntity<SparePartUsedGetDTO> save(@Valid @RequestBody SparePartUsedPostDTO dto) {
        SparePartUsedGetDTO created = sparePartUsedService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<SparePartUsedGetDTO> update(@PathVariable Long id, @Valid @RequestBody SparePartUsedPutDTO dto) {
        SparePartUsedGetDTO updated = sparePartUsedService.update(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sparePartUsedService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}