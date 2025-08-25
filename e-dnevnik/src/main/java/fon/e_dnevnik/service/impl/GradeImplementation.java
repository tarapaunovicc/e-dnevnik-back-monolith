package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.GradeRepository;
import fon.e_dnevnik.dao.StudentRepository;
import fon.e_dnevnik.dao.TeacherRepository;
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
import java.util.stream.Collectors;

@Service
public class GradeImplementation implements ServiceInterface<GradeDTO> {

    private GradeRepository gradeRepository;
    private ModelMapper modelMapper;
    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    public GradeImplementation(GradeRepository gradeRepository, ModelMapper modelMapper, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.gradeRepository = gradeRepository;
        this.modelMapper = modelMapper;
        this.studentRepository=studentRepository;
        this.teacherRepository=teacherRepository;
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
    Student student = studentRepository.findById(gradeDTO.getId().getStudentusername())
            .orElseThrow(() -> new Exception("Student not found"));

    Teacher teacher = teacherRepository.findById(gradeDTO.getId().getTeacherusername())
            .orElseThrow(() -> new Exception("Teacher not found"));

    Grade grade = new Grade();

    GradePK gradePK = new GradePK();
    gradePK.setStudentusername(student.getUsername());
    gradePK.setTeacherusername(teacher.getUsername());

    grade.setId(gradePK);
    grade.setStudent(student);
    grade.setTeacher(teacher);
    grade.setDate(LocalDate.now());
    grade.setGrade(gradeDTO.getGrade());

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
    public List<GradeDTO> findByStudentAndTeacher(String studentUsername, String teacherUsername) {
        List<Grade> grades = gradeRepository.findByIdStudentusernameAndIdTeacherusername(studentUsername, teacherUsername);
        return grades.stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }
}
