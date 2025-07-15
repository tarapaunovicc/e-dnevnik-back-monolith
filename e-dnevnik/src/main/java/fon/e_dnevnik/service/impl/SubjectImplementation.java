package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.SubjectRepository;
import fon.e_dnevnik.dto.SubjectDTO;
import fon.e_dnevnik.entity.Subject;
import fon.e_dnevnik.service.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectImplementation implements ServiceInterface<SubjectDTO> {

    private SubjectRepository subjectRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SubjectImplementation(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SubjectDTO> findAll() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDTO> subjectDTOS = new ArrayList<>();

        for(Subject subject: subjects){
            SubjectDTO subjectDTO = modelMapper.map(subject, SubjectDTO.class);
            subjectDTOS.add(subjectDTO);
        }
        return subjectDTOS;
    }

    @Override
    public SubjectDTO findById(Object id) throws Exception {
        Optional<Subject> subject=subjectRepository.findById((Integer) id);
        SubjectDTO subjectDTO;
        if(subject.isPresent()) {
            subjectDTO = modelMapper.map(subject.get(), SubjectDTO.class);
            return subjectDTO;
        }
        else{
            throw new Exception("Ne postoji predmet");
        }
    }

    @Override
    public SubjectDTO save(SubjectDTO subject) throws Exception {
        return null;
    }
}
