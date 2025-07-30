package com.mikadev.autocontrolapi.vehicle;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // findAll

    @GetMapping
    public ResponseEntity<List<VehicleGetDTO>> findAll(){
        List<VehicleGetDTO> vehicleGetDTOList = vehicleService.findAll();
        return ResponseEntity.ok(vehicleGetDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(vehicleService.findById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No source was found");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody VehiclePostDTO vehicle){
        try{
            VehicleGetDTO vehicleSaved = vehicleService.save(vehicle);
            URI location = URI.create("api/v1/vehicles/" + vehicleSaved.id());
            return ResponseEntity.created(location).body(vehicleSaved);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("No se creo el recurso. " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody VehicleUpdateDTO vehicle) {
        try {
            VehicleGetDTO vehicleUpdated = vehicleService.update(id, vehicle);
            return ResponseEntity.ok(vehicleUpdated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "No se realizo la actualizacion. " + e.getMessage()
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
            vehicleService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Recurso eliminado correctamente");
    }
}
