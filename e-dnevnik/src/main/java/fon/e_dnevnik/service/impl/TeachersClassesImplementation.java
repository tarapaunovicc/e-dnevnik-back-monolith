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
        List<TeachersClassesDTO> dtos = new ArrayList<>();
        for (TeachersClasses tc : teachersClasses) {
            TeachersClassesDTO dto = new TeachersClassesDTO();
            if (tc.getId() != null) {
                dto.setClassid(tc.getId().getClassid());
                dto.setTeacherusername(tc.getId().getTeacherusername());
            } else {
                dto.setClassid(tc.getCl() != null ? tc.getCl().getClassId() : null);
                dto.setTeacherusername(tc.getTeacher() != null ? tc.getTeacher().getUsername() : null);
            }
            if (tc.getCl() != null) {
                dto.setCl(modelMapper.map(tc.getCl(), ClassDTO.class));
            }
            dtos.add(dto);
        }
        return dtos;
    }



}
