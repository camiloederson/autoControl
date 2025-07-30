package com.mikadev.autocontrolapi.sparePartCategory;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sparePartCategories")
public class SparePartCategoryController {

    private final SparePartCategoryService sparePartCategoryService;

    public SparePartCategoryController(SparePartCategoryService sparePartCategoryService) {
        this.sparePartCategoryService = sparePartCategoryService;
    }

    // findAll
    @GetMapping
    public ResponseEntity<List<SparePartCategoryGetDTO>> findAll(){
        return new ResponseEntity<>(sparePartCategoryService.findAll(), HttpStatus.OK);
    }

    // findById
    @GetMapping("/{id}")
    public ResponseEntity<SparePartCategoryGetDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(sparePartCategoryService.findById(id), HttpStatus.OK);
    }

    // save
    @PostMapping
    public ResponseEntity<SparePartCategoryGetDTO> save(@Valid @RequestBody SparePartCategoryPostDTO dto){
        return new ResponseEntity<>(sparePartCategoryService.save(dto), HttpStatus.CREATED);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<SparePartCategoryGetDTO> update(@PathVariable Long id, @Valid @RequestBody SparePartCategoryPutDTO dto){
        return new ResponseEntity<>(sparePartCategoryService.update(id, dto), HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        sparePartCategoryService.delete(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
