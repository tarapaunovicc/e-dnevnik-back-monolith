package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dto.TeacherDTO;
import fon.e_dnevnik.entity.Class;
import fon.e_dnevnik.dao.ClassRepository;
import fon.e_dnevnik.dto.ClassDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.service.ServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassImplementation implements ServiceInterface<ClassDTO> {

    private ClassRepository classRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ClassImplementation(ClassRepository classRepository, ModelMapper modelMapper) {
        this.classRepository = classRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ClassDTO> findAll() {

        List<Class> classes = classRepository.findAll();

        List<ClassDTO> classDTOS = new ArrayList<>();

        for(Class cl :classes){
            ClassDTO classDTO = modelMapper.map(cl, ClassDTO.class);
            classDTO.setClassTeacher(modelMapper.map(cl.getClassTeacher(), TeacherDTO.class));
            classDTOS.add(classDTO);
        }
        return classDTOS;
    }

    @Override
    public ClassDTO findById(Object id) throws Exception {
        Optional<Class> cl=classRepository.findById((int) id);
        ClassDTO classDTO;
        if(cl.isPresent()) {
            classDTO = modelMapper.map(cl.get(), ClassDTO.class);
            classDTO.setClassTeacher(modelMapper.map(cl.get().getClassTeacher(), TeacherDTO.class));
            return classDTO;
        }
        else{
            throw new Exception("Ne postoji odeljenje");
        }    }

    @Override
    public ClassDTO save(ClassDTO classDTO) throws Exception {
        return null;
    }

    public ClassDTO findByClassTeacherUsername(String username)throws Exception {
        Class cl = classRepository.findByClassTeacherUsername(username);
        if (cl != null) {
            ClassDTO classDTO = modelMapper.map(cl, ClassDTO.class);
            return classDTO;
        } else {
            return null;
        }

    }
}
