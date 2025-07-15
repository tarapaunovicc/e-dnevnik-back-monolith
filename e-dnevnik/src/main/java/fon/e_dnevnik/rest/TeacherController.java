package fon.e_dnevnik.rest;

import fon.e_dnevnik.dto.TeacherDTO;
import fon.e_dnevnik.dto.TeachersClassesDTO;
import fon.e_dnevnik.service.impl.TeacherImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private TeacherImplementation teacherImplementation;

    @Autowired
    public TeacherController(TeacherImplementation teacherImplementation) {
        this.teacherImplementation = teacherImplementation;
    }
    @GetMapping
    public List<TeacherDTO> findAll(){
        return teacherImplementation.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<TeacherDTO> findById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(teacherImplementation.findById((String)id));
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> save(@Valid @RequestBody TeacherDTO teacherDTO) throws Exception {
        return new ResponseEntity<>(teacherImplementation.save(teacherDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{id}/classes")
    public List<TeachersClassesDTO> getTeachersClasses(@PathVariable String id) throws Exception {
        return teacherImplementation.getTeachersClasses(id);
    }
}
