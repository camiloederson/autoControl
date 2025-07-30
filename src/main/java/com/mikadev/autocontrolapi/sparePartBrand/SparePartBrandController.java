package com.mikadev.autocontrolapi.sparePartBrand;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sparePartBrands")
public class SparePartBrandController {
    private final SparePartBrandService sparePartBrandService;

    public SparePartBrandController(SparePartBrandService sparePartBrandService) {
        this.sparePartBrandService = sparePartBrandService;
    }

    // findAll
    @GetMapping
    public ResponseEntity<List<SparePartBrandGetDTO>> findAll() {
        return new ResponseEntity<>(sparePartBrandService.findAll(), HttpStatus.OK);
    }

    // findById
    @GetMapping("/{id}")
    public ResponseEntity<SparePartBrandGetDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(sparePartBrandService.findById(id), HttpStatus.OK);
    }

    // save
    @PostMapping
    public ResponseEntity<SparePartBrandGetDTO> create(@Valid @RequestBody SparePartBrandPostDTO sparePartBrandPostDTO) {
        return new ResponseEntity<>(sparePartBrandService.save(sparePartBrandPostDTO), HttpStatus.CREATED);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<SparePartBrandGetDTO> update(@Valid @RequestBody SparePartBrandPutDTO sparePartBrandPutDTO, @PathVariable("id") Long id) {
        return new ResponseEntity<>(sparePartBrandService.update(id, sparePartBrandPutDTO), HttpStatus.OK);
    }

    // delete
    @DeleteMapping
    public ResponseEntity<SparePartBrandGetDTO> delete(@PathVariable Long id) {
        sparePartBrandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
