package fon.e_dnevnik.rest;

import fon.e_dnevnik.dto.SubjectDTO;
import fon.e_dnevnik.service.impl.SubjectImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController
{
    private SubjectImplementation subjectImplementation;

    @Autowired
    public SubjectController(SubjectImplementation subjectImplementation) {
        this.subjectImplementation = subjectImplementation;
    }

    @GetMapping
    public List<SubjectDTO> findAll(){
        return subjectImplementation.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<SubjectDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(subjectImplementation.findById((Integer)id));
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> save(@Valid @RequestBody SubjectDTO subjectDTO) throws Exception {
        return new ResponseEntity<>(subjectImplementation.save(subjectDTO), HttpStatus.CREATED);
    }
}
