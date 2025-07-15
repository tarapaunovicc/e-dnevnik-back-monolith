package fon.e_dnevnik.rest;

import fon.e_dnevnik.dto.TeachersClassesDTO;
import fon.e_dnevnik.entity.TeachersClasses;
import fon.e_dnevnik.service.impl.TeachersClassesImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teachersclasses")
public class TeachersClassesController {
    private TeachersClassesImplementation teachersClassesImplementation;

    @Autowired
    public TeachersClassesController(TeachersClassesImplementation teachersClassesImplementation) {
        this.teachersClassesImplementation = teachersClassesImplementation;
    }
    @GetMapping("{username}")
    public List<TeachersClassesDTO> findByTeacherUsername(@PathVariable String username){
        return teachersClassesImplementation.findByTeacherUsername(username);
    }
}
