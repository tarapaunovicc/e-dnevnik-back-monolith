package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.LessonRepository;
import fon.e_dnevnik.dao.TeachersClassesRepository;
import fon.e_dnevnik.dto.LessonDTO;
import fon.e_dnevnik.entity.Lesson;
import fon.e_dnevnik.entity.TeachersClasses;
import fon.e_dnevnik.entity.primarykey.LessonPK;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.service.ServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonImplementation implements ServiceInterface<LessonDTO> {

    private final LessonRepository lessonRepository;
    private final TeachersClassesRepository teachersClassesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LessonImplementation(LessonRepository lessonRepository, ModelMapper modelMapper, TeachersClassesRepository teachersClassesRepository) {
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
        this.teachersClassesRepository=teachersClassesRepository;
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
        Optional<Lesson> lesson=lessonRepository.findById((LessonPK) id);
        LessonDTO lessonDTO;
        if(lesson.isPresent()) {
            lessonDTO = modelMapper.map(lesson.get(), LessonDTO.class);
            return lessonDTO;
        }
        else{
            throw new Exception("Ne postoji cas");
        }    }

public LessonDTO save(LessonDTO lessonDTO) throws Exception {
    TeachersClasses tc = teachersClassesRepository
            .findByIdClassidAndIdTeacherusername(
                    lessonDTO.getClassid(),
                    lessonDTO.getTeacherUsername()
            );

    Lesson lesson = new Lesson();
    lesson.setDate(lessonDTO.getDate());
    lesson.setClassOrdinalNumber(lessonDTO.getClassOrdinalNumber());
    lesson.setCurriculum(lessonDTO.getCurriculum());

    lesson.setTeachersClasses(tc);

    Lesson savedLesson = lessonRepository.save(lesson);

    LessonDTO savedLessonDTO = new LessonDTO();
    savedLessonDTO.setLessonid(savedLesson.getLessonid());
    savedLessonDTO.setClassid(tc.getId().getClassid());
    savedLessonDTO.setTeacherUsername(tc.getId().getTeacherusername());
    savedLessonDTO.setDate(savedLesson.getDate());
    savedLessonDTO.setClassOrdinalNumber(savedLesson.getClassOrdinalNumber());
    savedLessonDTO.setCurriculum(savedLesson.getCurriculum());

    return savedLessonDTO;
}
}
