package fon.e_dnevnik.rest;

import fon.e_dnevnik.dto.ClassDTO;
import fon.e_dnevnik.service.impl.ClassImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private ClassImplementation classImplementation;

    @Autowired
    public ClassController(ClassImplementation classImplementation) {
        this.classImplementation = classImplementation;
    }

    @GetMapping
    public List<ClassDTO> findAll(){
        return classImplementation.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ClassDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(classImplementation.findById((Integer)id));
    }

    @PostMapping
    public ResponseEntity<ClassDTO> save(@Valid @RequestBody ClassDTO classDTO) throws Exception {
        return new ResponseEntity<>(classImplementation.save(classDTO), HttpStatus.CREATED);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<ClassDTO> findByClassTeacherUsername(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(classImplementation.findByClassTeacherUsername((String)id));
    }


}
