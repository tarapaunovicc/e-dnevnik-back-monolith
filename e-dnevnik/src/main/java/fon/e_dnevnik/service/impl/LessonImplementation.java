package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.LessonRepository;
import fon.e_dnevnik.dto.LessonDTO;
import fon.e_dnevnik.entity.Lesson;
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

    private LessonRepository lessonRepository;
    private ModelMapper modelMapper;

    @Autowired
    public LessonImplementation(LessonRepository lessonRepository, ModelMapper modelMapper) {
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public LessonDTO save(LessonDTO lessonDTO) throws Exception {
        Lesson lesson = modelMapper.map(lessonDTO, Lesson.class);
        Lesson savedLesson = lessonRepository.save(lesson);
        return modelMapper.map(savedLesson, LessonDTO.class);    }
}
