package com.mikadev.autocontrolapi.customer;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://autocontrol-production.up.railway.app"
})
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerGetDTO>> findAll(){
            List<CustomerGetDTO> customerGetDTOList = customerService.findAll();
            return ResponseEntity.ok(customerGetDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
            return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CustomerPostDTO customerPostDTO){
            return ResponseEntity.ok(customerService.save(customerPostDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateDTO customerUpdateDTO){
            return ResponseEntity.ok(customerService.update(customerUpdateDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
            customerService.deleteById(id);
            return ResponseEntity.ok().body("Customer with id " + id + " was deleted");
    }
}
