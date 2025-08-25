package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.TeacherRepository;
import fon.e_dnevnik.dao.TeachersClassesRepository;
import fon.e_dnevnik.dto.SubjectDTO;
import fon.e_dnevnik.dto.TeacherDTO;
import fon.e_dnevnik.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.service.ServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherImplementation implements ServiceInterface<TeacherDTO> {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TeacherImplementation(TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TeacherDTO> findAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOS = new ArrayList<>();

        for(Teacher teacher: teachers){
            TeacherDTO teacherDTO = modelMapper.map(teacher, TeacherDTO.class);
            teacherDTO.setSubject(modelMapper.map(teacher.getSubject(), SubjectDTO.class));
            teacherDTOS.add(teacherDTO);
        }
        return teacherDTOS;
        //ne koristis
    }

    @Override
    public TeacherDTO findById(Object id) throws Exception {
        Optional<Teacher> teacherOpt = teacherRepository.findById((String) id);
        if (teacherOpt.isEmpty()) {
            throw new Exception("Ne postoji profesor");
        }
        Teacher teacher = teacherOpt.get();
        TeacherDTO teacherDTO = modelMapper.map(teacher, TeacherDTO.class);
        if (teacher.getSubject() != null) {
            SubjectDTO subjectDTO = modelMapper.map(teacher.getSubject(), SubjectDTO.class);
            teacherDTO.setSubject(subjectDTO);
        } else {
            teacherDTO.setSubject(null);
        }

        return teacherDTO;
    }

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) throws Exception {
        return null;
    }

}
