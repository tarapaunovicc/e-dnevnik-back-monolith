package fon.e_dnevnik.rest;

import fon.e_dnevnik.dto.StudentDTO;
import fon.e_dnevnik.service.impl.StudentImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentImplementation studentImplementation;

    @Autowired
    public StudentController(StudentImplementation studentImplementation) {
        this.studentImplementation = studentImplementation;
    }
    @GetMapping
    public List<StudentDTO> findAll(){
        return studentImplementation.findAll();
    }

    @GetMapping("{username}")
    public ResponseEntity<StudentDTO> findById(@PathVariable String username) throws Exception {
        return ResponseEntity.ok().body(studentImplementation.findById(username));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody StudentDTO studentDTO) throws Exception {
        return new ResponseEntity<>(studentImplementation.save(studentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/class/{classid}")
    public List<StudentDTO> findByStudentClassClassId(@PathVariable int classid){
        System.out.println("Uslo je u kontrolera");
        return studentImplementation.findByStudentClassClassId(classid);
    }
}
