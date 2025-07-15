package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.TeachersClassesRepository;
import fon.e_dnevnik.dto.*;
import fon.e_dnevnik.entity.Grade;
import fon.e_dnevnik.entity.Teacher;
import fon.e_dnevnik.entity.TeachersClasses;
import fon.e_dnevnik.service.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeachersClassesImplementation implements ServiceInterface<TeachersClassesDTO> {
    private ModelMapper modelMapper;
    private TeachersClassesRepository teachersClassesRepository;
    @Autowired
    public TeachersClassesImplementation(ModelMapper modelMapper, TeachersClassesRepository teachersClassesRepository) {
        this.modelMapper = modelMapper;
        this.teachersClassesRepository = teachersClassesRepository;
    }

    @Override
    public List<TeachersClassesDTO> findAll() {
        List<TeachersClasses> teachersClasses = teachersClassesRepository.findAll();
        List<TeachersClassesDTO> teachersClassesDTOS = new ArrayList<>();

        for(TeachersClasses teacheraclass: teachersClasses){
            TeachersClassesDTO teachersClassesDTO = modelMapper.map(teacheraclass, TeachersClassesDTO.class);
            teachersClassesDTOS.add(teachersClassesDTO);
        }
        return teachersClassesDTOS;    }

    @Override
    public TeachersClassesDTO findById(Object id) throws Exception {
        return null;
    }

    @Override
    public TeachersClassesDTO save(TeachersClassesDTO teachersClassesDTO) throws Exception {
        return null;
    }


    public List<TeachersClassesDTO> findByTeacherUsername(String username) {
        List<TeachersClasses> teachersClasses = teachersClassesRepository.findByTeacherUsername(username);
        List<TeachersClassesDTO> teachersClassesDTOS = new ArrayList<>();
        for(TeachersClasses g: teachersClasses){
            TeachersClassesDTO teachersClassesDTO = modelMapper.map(g, TeachersClassesDTO.class);
            teachersClassesDTO.setCl(modelMapper.map(g.getCl(), ClassDTO.class));
            teachersClassesDTO.setTeacher(modelMapper.map(g.getTeacher(),TeacherDTO.class));
            teachersClassesDTOS.add(teachersClassesDTO);
        }

        System.out.println(teachersClassesDTOS);
        return teachersClassesDTOS;
    }


}
