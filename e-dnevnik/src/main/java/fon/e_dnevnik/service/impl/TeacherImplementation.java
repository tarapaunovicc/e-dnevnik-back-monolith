package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.TeacherRepository;
import fon.e_dnevnik.dao.TeachersClassesRepository;
import fon.e_dnevnik.dto.ClassDTO;
import fon.e_dnevnik.dto.SubjectDTO;
import fon.e_dnevnik.dto.TeacherDTO;
import fon.e_dnevnik.dto.TeachersClassesDTO;
import fon.e_dnevnik.entity.Teacher;
import fon.e_dnevnik.entity.TeachersClasses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.service.ServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherImplementation implements ServiceInterface<TeacherDTO> {
    private TeacherRepository teacherRepository;
    private ModelMapper modelMapper;

    private TeachersClassesRepository teachersClassesRepository;
    @Autowired
    public TeacherImplementation(TeacherRepository teacherRepository, ModelMapper modelMapper, TeachersClassesRepository teachersClassesRepository) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
        this.teachersClassesRepository=teachersClassesRepository;
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
    }

    @Override
    public TeacherDTO findById(Object id) throws Exception {
        Optional<Teacher> teacher=teacherRepository.findById((String) id);
        TeacherDTO teacherDTO;
        if(teacher.isPresent()) {
            teacherDTO = modelMapper.map(teacher.get(), TeacherDTO.class);
            teacherDTO.setSubject(modelMapper.map(teacher.get().getSubject(), SubjectDTO.class));
            return teacherDTO;
        }
        else{
            throw new Exception("Ne postoji profesor");
        }
    }

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) throws Exception {
        return null;
    }

    public List<TeachersClassesDTO> getTeachersClasses(String username){
        List<TeachersClasses> teachersClasses = teachersClassesRepository.findByTeacherUsername(username);
        List<TeachersClassesDTO> teachersClassesDTOS = new ArrayList<>();

        for(TeachersClasses tc: teachersClasses){
            TeachersClassesDTO teachersClassesDTO = modelMapper.map(tc, TeachersClassesDTO.class);
            teachersClassesDTO.setTeacher(modelMapper.map(tc.getTeacher(), TeacherDTO.class));
            teachersClassesDTO.setCl(modelMapper.map(tc.getClass(), ClassDTO.class));
            teachersClassesDTOS.add(teachersClassesDTO);
        }
        System.out.println("ovo"+ teachersClassesDTOS);
        return teachersClassesDTOS;
    }
}
