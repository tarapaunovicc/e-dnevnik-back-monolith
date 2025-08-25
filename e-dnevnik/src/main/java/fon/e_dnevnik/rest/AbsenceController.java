package fon.e_dnevnik.rest;

import fon.e_dnevnik.dto.AbsenceDTO;
import fon.e_dnevnik.entity.primarykey.AbsencePK;
import fon.e_dnevnik.service.impl.AbsenceImplementation;
import fon.e_dnevnik.service.impl.LessonImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsenceController {
    private AbsenceImplementation absenceImplementation;


    @Autowired
    public AbsenceController(AbsenceImplementation absenceImplementation) {
        this.absenceImplementation = absenceImplementation;
    }

    @GetMapping
    public List<AbsenceDTO> findAll() {
        return absenceImplementation.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<AbsenceDTO> findById(@PathVariable AbsencePK id) throws Exception {
        return ResponseEntity.ok().body(absenceImplementation.findById((AbsencePK) id));
    }

    @PostMapping("/new")
    public ResponseEntity<AbsenceDTO> save(@RequestBody AbsenceDTO absenceDTO) throws Exception {
        return new ResponseEntity<>(absenceImplementation.save(absenceDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all/{username}")
    public List<AbsenceDTO> findByStudentUsername(@PathVariable String username) {
        return absenceImplementation.findByStudentUsername(username);
    }

    @PutMapping("/modify")
    public ResponseEntity<AbsenceDTO> modifyAbsence(@RequestBody AbsenceDTO absenceDTO) throws Exception {
        AbsenceDTO updatedAbsence = absenceImplementation.modify(absenceDTO.getId(), absenceDTO.isExcused(), absenceDTO.isIsfinal());
        return ResponseEntity.ok(updatedAbsence);
    }

}
