package fon.e_dnevnik.rest;

import fon.e_dnevnik.dto.GradeDTO;
import fon.e_dnevnik.dto.help.GradeFilterRequest;
import fon.e_dnevnik.service.impl.GradeImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {
    private final GradeImplementation gradeImplementation;

    @Autowired
    public GradeController(GradeImplementation gradeImplementation) {
        this.gradeImplementation = gradeImplementation;
    }

    @GetMapping
    public List<GradeDTO> findAll(){
        return gradeImplementation.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<GradeDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(gradeImplementation.findById(id));
    }

    @PostMapping
    public ResponseEntity<GradeDTO> save(@Valid @RequestBody GradeDTO gradeDTO) throws Exception{
        return new ResponseEntity<>(gradeImplementation.save(gradeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/student/{username}")
    public List<GradeDTO> getGrades(@PathVariable String username){
        return gradeImplementation.findByStudentUsername(username);
    }
    @PostMapping("/by-student-teacher")
    public List<GradeDTO> findByStudentAndTeacher(@RequestBody GradeFilterRequest request) {
        return gradeImplementation.findByStudentAndTeacher(
                request.getStudentusername(),
                request.getTeacherusername()
        );
    }
    @GetMapping("/student2/{username}")
    public List<GradeDTO> getGradesWithSubjects(@PathVariable String username){
        return gradeImplementation.findByStudentUsernameWithSubject(username);
    }
}
