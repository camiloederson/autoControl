package com.mikadev.autocontrolapi.vehicleBrand;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/vehicleBrands")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleBrandController {
    private final VehicleBrandService vehicleBrandService;

    public VehicleBrandController(VehicleBrandService vehicleBrandService) {
        this.vehicleBrandService = vehicleBrandService;
    }

    // findById
    @GetMapping("/{id}")
    public ResponseEntity<VehicleBrandGetDTO> findById(@PathVariable Long id){
            VehicleBrandGetDTO vehicleBrandGetDTO = vehicleBrandService.findById(id);
            return ResponseEntity.ok(vehicleBrandGetDTO);
    }

    // findAll
    @GetMapping
    public ResponseEntity<List<VehicleBrandGetDTO>> findAll(){
        List<VehicleBrandGetDTO> vehicleBrandGetDTOList = vehicleBrandService.findAll();
        return ResponseEntity.ok(vehicleBrandGetDTOList);
    }

    // save
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody VehicleBrandPostDTO vehicleBrand){
            VehicleBrandGetDTO vehicleBrandGetDTOSaved =
                    vehicleBrandService.save(vehicleBrand);
            URI location = URI.create(("api/v1/vehicleBrands/" + vehicleBrandGetDTOSaved.id()));
            return ResponseEntity.created(location).body(vehicleBrandGetDTOSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody VehicleBrandPostDTO vehicleBrand){
            VehicleBrandGetDTO vehicleBrandGetDTOSaved = vehicleBrandService.update(id, vehicleBrand);
            return ResponseEntity.ok(vehicleBrandGetDTOSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
            vehicleBrandService.delete(id);
            return ResponseEntity.ok().body("Recurso eliminado");

    }
}
