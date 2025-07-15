package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.GradeRepository;
import fon.e_dnevnik.dto.GradeDTO;
import fon.e_dnevnik.entity.Grade;
import fon.e_dnevnik.entity.Student;
import fon.e_dnevnik.entity.Teacher;
import fon.e_dnevnik.entity.primarykey.GradePK;
import fon.e_dnevnik.service.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GradeImplementation implements ServiceInterface<GradeDTO> {

    private GradeRepository gradeRepository;
    private ModelMapper modelMapper;

    public GradeImplementation(GradeRepository gradeRepository, ModelMapper modelMapper) {
        this.gradeRepository = gradeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GradeDTO> findAll() {
        List<Grade> grades = gradeRepository.findAll();
        List<GradeDTO> gradeDTOS = new ArrayList<>();

        for(Grade grade: grades){
            GradeDTO gradeDTO = modelMapper.map(grade, GradeDTO.class);
            gradeDTOS.add(gradeDTO);
        }
        return gradeDTOS;
    }

    @Override
    public GradeDTO findById(Object id) throws Exception {
        Optional<Grade> grade=gradeRepository.findById((GradePK) id);
        GradeDTO gradeDTO;
        if(grade.isPresent()) {
            gradeDTO = modelMapper.map(grade.get(), GradeDTO.class);
            return gradeDTO;
        }
        else{
            throw new Exception("Ne postoji ocena");
        }    }

    @Override
    public GradeDTO save(GradeDTO gradeDTO) throws Exception {
        Grade grade = modelMapper.map(gradeDTO, Grade.class);
        grade.setStudent(modelMapper.map(gradeDTO.getStudent(), Student.class));
        grade.setTeacher(modelMapper.map(gradeDTO.getTeacher(), Teacher.class));
        GradePK gradePK = new GradePK(grade.getStudent().getUsername(), grade.getTeacher().getUsername(), 0);
        grade.setId(gradePK);
        grade.setDate(LocalDate.now());
        Grade savedGrade = gradeRepository.save(grade);
        return modelMapper.map(savedGrade, GradeDTO.class);
    }

    public List<GradeDTO> findByStudentUsername(String username){
        List<Grade> grades = gradeRepository.findByStudentUsername(username);
        List<GradeDTO> gradeDTOS = new ArrayList<>();

        for(Grade grade: grades){
            GradeDTO gradeDTO = modelMapper.map(grade, GradeDTO.class);
            gradeDTOS.add(gradeDTO);
        }
        return gradeDTOS;
    }
}
