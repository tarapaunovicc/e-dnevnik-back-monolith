package fon.e_dnevnik.rest;

import fon.e_dnevnik.dto.LessonDTO;
import fon.e_dnevnik.entity.primarykey.LessonPK;
import fon.e_dnevnik.service.impl.LessonImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private LessonImplementation lessonImplementation;

    @Autowired
    public LessonController(LessonImplementation lessonImplementation) {
        this.lessonImplementation = lessonImplementation;
    }
    @GetMapping
    public List<LessonDTO> findAll(){
        return lessonImplementation.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<LessonDTO> findById(@PathVariable LessonPK id) throws Exception {
        return ResponseEntity.ok().body(lessonImplementation.findById((LessonPK)id));
    }

    @PostMapping("/new")
    public ResponseEntity<LessonDTO> save( @RequestBody LessonDTO lessonDTO) throws Exception {
        System.out.println(lessonDTO);
        return new ResponseEntity<>(lessonImplementation.save(lessonDTO), HttpStatus.CREATED);
    }
}
