package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.AbsenceRepository;
import fon.e_dnevnik.dao.LessonRepository;
import fon.e_dnevnik.dao.TeacherRepository;
import fon.e_dnevnik.dto.AbsenceDTO;
import fon.e_dnevnik.dto.LessonDTO;
import fon.e_dnevnik.entity.Absence;
import fon.e_dnevnik.entity.Lesson;
import fon.e_dnevnik.entity.Teacher;
import fon.e_dnevnik.entity.primarykey.AbsencePK;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.service.ServiceInterface;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AbsenceImplementation implements ServiceInterface<AbsenceDTO> {

    private final AbsenceRepository absenceRepository;
    private final TeacherRepository teacherRepository;
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AbsenceImplementation(AbsenceRepository absenceRepository, ModelMapper modelMapper, LessonRepository lessonRepository,
                                 TeacherRepository teacherRepository) {
        this.absenceRepository = absenceRepository;
        this.modelMapper = modelMapper;
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<AbsenceDTO> findAll() {
        List<Absence> absences = absenceRepository.findAll();
        List<AbsenceDTO> absenceDTOS = new ArrayList<>();
        for(Absence absence :absences){
            AbsenceDTO absenceDTO = modelMapper.map(absence, AbsenceDTO.class);
            absenceDTOS.add(absenceDTO);
        }
        return absenceDTOS;
    }

    @Override
    public AbsenceDTO findById(Object id) throws Exception {
        Optional<Absence> absence=absenceRepository.findById((AbsencePK) id);
        if(absence.isPresent()) {
            return modelMapper.map(absence.get(), AbsenceDTO.class);
        }
        else{
            throw new Exception("Ne postoji odsustvo");
        }    }

    @Override
    public AbsenceDTO save(AbsenceDTO absenceDTO) throws Exception {
        Absence absence = modelMapper.map(absenceDTO, Absence.class);
        Absence savedAbsence = absenceRepository.save(absence);
        return modelMapper.map(savedAbsence, AbsenceDTO.class);
    }
@Transactional(readOnly = true)
public List<AbsenceDTO> findByStudentUsername(String username) {
    List<Absence> absences = absenceRepository.findByIdStudentusername(username);
    if (absences.isEmpty()) return List.of();

    Set<Integer> lessonIds = absences.stream()
            .map(a -> a.getId().getLesson_id())
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());

    List<Lesson> lessons = lessonRepository.findByLessonidIn(lessonIds);

    Map<Integer, Lesson> lessonById = new HashMap<>(lessons.size());
    for (Lesson l : lessons) {
        lessonById.put(l.getLessonid(), l);
    }

    Set<String> teacherUsernames = lessons.stream()
            .map(Lesson::getTeacherusername)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());

    List<Teacher> teachers = teacherUsernames.isEmpty()
            ? List.of()
            : teacherRepository.findByUsernameIn(teacherUsernames);

    Map<String, String> subjectByTeacher = new HashMap<>(teachers.size());
    for (Teacher t : teachers) {
        String subjectName = (t.getSubject() != null) ? t.getSubject().getName() : null;
        subjectByTeacher.put(t.getUsername(), subjectName);
    }

    List<AbsenceDTO> dtos = new ArrayList<>(absences.size());
    for (Absence a : absences) {
        AbsenceDTO dto = modelMapper.map(a, AbsenceDTO.class);

        Lesson lesson = lessonById.get(a.getId().getLesson_id());
        if (lesson != null) {
            LessonDTO ldto = modelMapper.map(lesson, LessonDTO.class);

            try {
                if (ldto.getTeacherusername() == null) {
                    ldto.setTeacherusername(lesson.getTeacherusername());
                }
            } catch (Throwable ignore) {}

            dto.setLesson(ldto);

            String subj = subjectByTeacher.get(lesson.getTeacherusername());
            dto.setSubjectname(subj);
        } else {
            dto.setSubjectname(null);
        }
        dtos.add(dto);
    }
    return dtos;
}
    public AbsenceDTO modify(AbsencePK id, boolean excused, boolean isFinal) throws Exception {
        Optional<Absence> optionalAbsence = absenceRepository.findById(id);
        if (optionalAbsence.isPresent()) {
            Absence absence = optionalAbsence.get();
            absence.setExcused(excused);
            absence.setIsfinal(isFinal);
            Absence updatedAbsence = absenceRepository.save(absence);
            return modelMapper.map(updatedAbsence, AbsenceDTO.class);
        } else {
            throw new Exception("Ne postoji odsustvo sa zadatim ID-jem");
        }
    }
}
