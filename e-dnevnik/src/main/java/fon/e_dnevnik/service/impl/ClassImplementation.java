package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.entity.Class;
import fon.e_dnevnik.dao.ClassRepository;
import fon.e_dnevnik.dto.ClassDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.service.ServiceInterface;
import java.util.List;
import java.util.Optional;

@Service
public class ClassImplementation implements ServiceInterface<ClassDTO> {

    private final ClassRepository classRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClassImplementation(ClassRepository classRepository, ModelMapper modelMapper) {
        this.classRepository = classRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ClassDTO> findAll() {
        return null;
    }

    @Override
    public ClassDTO findById(Object id) throws Exception {
        Optional<Class> cl=classRepository.findById((int) id);
        if(cl.isPresent()) {
            return modelMapper.map(cl.get(), ClassDTO.class);
        }
        else{
            throw new Exception("Ne postoji odeljenje");
        }    }

    @Override
    public ClassDTO save(ClassDTO classDTO) throws Exception {
        return null;
    }

    public ClassDTO findByClassTeacherUsername(String username){
        Class cl = classRepository.findByClassTeacherUsername(username);
        if (cl != null) {
            return modelMapper.map(cl, ClassDTO.class);
        } else {
            return null;
        }

    }
}
