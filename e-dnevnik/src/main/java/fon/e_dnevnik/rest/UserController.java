package fon.e_dnevnik.rest;

import fon.e_dnevnik.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fon.e_dnevnik.service.impl.UserImplementation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserImplementation userImplementation;
    @Autowired
    public UserController(UserImplementation userImplementation) {
        this.userImplementation = userImplementation;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok().body(userImplementation.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(userImplementation.findById((String)id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(userImplementation.save(userDTO), HttpStatus.CREATED);
    }
}
