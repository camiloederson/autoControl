package com.mikadev.autocontrolapi.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    // Find All
    @GetMapping
    public ResponseEntity<List<UserGetDTO>> findAll() {
        List<UserGetDTO> userGetDTOList = userService.findAll();
        if(userGetDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(userGetDTOList);
    }

    // FindById
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
            return ResponseEntity.ok(userService.findById(id));
        }


    // Save
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UserPostDTO userPostDTO) {
        UserGetDTO userSaved = userService.save(userPostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    // Update
    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid @RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Long id){

            UserGetDTO userGetDTO = userService.update(userUpdateDTO, id);
            return ResponseEntity.ok(userGetDTO);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
            userService.deleteById(id);
            return ResponseEntity.ok("User with id " + id + " was deleted from database");
    }
}
