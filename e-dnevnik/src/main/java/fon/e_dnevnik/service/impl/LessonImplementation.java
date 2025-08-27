package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.AbsenceRepository;
import fon.e_dnevnik.dao.LessonRepository;
import fon.e_dnevnik.dto.AbsenceDTO;
import fon.e_dnevnik.dto.LessonDTO;
import fon.e_dnevnik.dto.help.NewLessonWithAbsencesResponse;
import fon.e_dnevnik.entity.Absence;
import fon.e_dnevnik.entity.Lesson;
import fon.e_dnevnik.entity.primarykey.AbsencePK;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.service.ServiceInterface;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LessonImplementation implements ServiceInterface<LessonDTO> {

    private final LessonRepository lessonRepository;
    private final AbsenceRepository absenceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LessonImplementation(LessonRepository lessonRepository, ModelMapper modelMapper, AbsenceRepository absenceRepository) {
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
        this.absenceRepository=absenceRepository;
    }

    @Override
    public List<LessonDTO> findAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        List<LessonDTO> lessonDTOS = new ArrayList<>();

        for(Lesson lesson: lessons){
            LessonDTO lessonDTO = modelMapper.map(lesson, LessonDTO.class);
            lessonDTOS.add(lessonDTO);
        }
        return lessonDTOS;
    }

    @Override
    public LessonDTO findById(Object id) throws Exception {
        Optional<Lesson> lesson=lessonRepository.findById((Integer) id);
        if(lesson.isPresent()) {
            return modelMapper.map(lesson.get(), LessonDTO.class);
        }
        else{
            throw new Exception("Ne postoji cas");
        }    }

public LessonDTO save(LessonDTO lessonDTO) throws Exception {
    Lesson lesson = new Lesson();
    lesson.setDate(lessonDTO.getDate());
    lesson.setClassOrdinalNumber(lessonDTO.getClassOrdinalNumber());
    lesson.setCurriculum(lessonDTO.getCurriculum());
    lesson.setTeacherusername(lessonDTO.getTeacherusername());
    lesson.setClassid(lessonDTO.getClassid());

    Lesson savedLesson = lessonRepository.save(lesson);

    LessonDTO savedLessonDTO = new LessonDTO();
    savedLessonDTO.setLessonid(savedLesson.getLessonid());
    savedLessonDTO.setClassid(savedLesson.getClassid());
    savedLessonDTO.setTeacherusername(savedLesson.getTeacherusername());
    savedLessonDTO.setDate(savedLesson.getDate());
    savedLessonDTO.setClassOrdinalNumber(savedLesson.getClassOrdinalNumber());
    savedLessonDTO.setCurriculum(savedLesson.getCurriculum());

    return savedLessonDTO;
}

    @Transactional
    public NewLessonWithAbsencesResponse createLessonAndAbsences(LessonDTO lessonDTO, List<String> studentUsernames) {
        Lesson lesson = new Lesson();
        lesson.setClassid(lessonDTO.getClassid());
        lesson.setTeacherusername(lessonDTO.getTeacherusername());
        lesson.setDate(lessonDTO.getDate());
        lesson.setClassOrdinalNumber(lessonDTO.getClassOrdinalNumber());
        lesson.setCurriculum(lessonDTO.getCurriculum());

        Lesson savedLesson = lessonRepository.save(lesson);

        LessonDTO savedLessonDTO = modelMapper.map(savedLesson, LessonDTO.class);
        try {
            if (savedLessonDTO.getTeacherusername() == null) {
                savedLessonDTO.setTeacherusername(savedLesson.getTeacherusername());
            }
        } catch (Throwable ignore) {}

        Set<String> uniqueStudents = (studentUsernames == null)
                ? Set.of()
                : studentUsernames.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        List<Absence> toSave = new ArrayList<>(uniqueStudents.size());
        for (String su : uniqueStudents) {
            Absence a = new Absence();
            AbsencePK pk = new AbsencePK(su, savedLesson.getLessonid());
            a.setId(pk);
            a.setExcused(false);
            a.setIsfinal(false);
            toSave.add(a);
        }

        List<Absence> savedAbs = toSave.isEmpty() ? List.of() : absenceRepository.saveAll(toSave);

        List<AbsenceDTO> absenceDTOs = new ArrayList<>(savedAbs.size());
        for (Absence e : savedAbs) {
            AbsenceDTO dto = modelMapper.map(e, AbsenceDTO.class);
            dto.setLesson(savedLessonDTO);
            absenceDTOs.add(dto);
        }

        NewLessonWithAbsencesResponse out = new NewLessonWithAbsencesResponse();
        out.setLesson(savedLessonDTO);
        out.setAbsences(absenceDTOs);
        return out;
    }

}
