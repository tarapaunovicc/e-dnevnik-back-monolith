package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.GradeRepository;
import fon.e_dnevnik.dao.TeacherRepository;
import fon.e_dnevnik.dto.GradeDTO;
import fon.e_dnevnik.entity.Grade;
import fon.e_dnevnik.entity.Teacher;
import fon.e_dnevnik.service.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GradeImplementation implements ServiceInterface<GradeDTO> {

    private final GradeRepository gradeRepository;
    private final ModelMapper modelMapper;
    private final TeacherRepository teacherRepository;


    public GradeImplementation(GradeRepository gradeRepository, ModelMapper modelMapper, TeacherRepository teacherRepository) {
        this.gradeRepository = gradeRepository;
        this.modelMapper = modelMapper;
        this.teacherRepository = teacherRepository;
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
        Optional<Grade> grade=gradeRepository.findById((Integer) id);
        if(grade.isPresent()) {
           return modelMapper.map(grade.get(), GradeDTO.class);
        }
        else{
            throw new Exception("Ne postoji ocena");
        }    }

    @Override
    public GradeDTO save(GradeDTO dto) throws Exception {
        if (dto.getStudentusername() == null || dto.getStudentusername().isBlank())
            throw new IllegalArgumentException("studentusername je obavezan.");
        if (dto.getTeacherusername() == null || dto.getTeacherusername().isBlank())
            throw new IllegalArgumentException("teacherusername je obavezan.");
        if (dto.getGrade() < 1 || dto.getGrade() > 5)
            throw new IllegalArgumentException("Ocena mora biti 1–5.");

        Grade entity = modelMapper.map(dto, Grade.class);
        if (entity.getDate() == null) {
            entity.setDate(LocalDate.now());
        }

        Grade saved = gradeRepository.save(entity);
        return modelMapper.map(saved, GradeDTO.class);
    }


    public List<GradeDTO> findByStudentUsername(String username){
        List<Grade> grades = gradeRepository.findByStudentusername(username);
        List<GradeDTO> gradeDTOS = new ArrayList<>();

        for(Grade grade: grades){
            GradeDTO gradeDTO = modelMapper.map(grade, GradeDTO.class);
            gradeDTOS.add(gradeDTO);
        }
        return gradeDTOS;
    }
    public List<GradeDTO> findByStudentAndTeacher(String studentUsername, String teacherUsername) {
        List<Grade> grades = gradeRepository.findByStudentusernameAndTeacherusername(studentUsername, teacherUsername);
        return grades.stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<GradeDTO> findByStudentUsernameWithSubject(String username) {
        List<Grade> grades = gradeRepository.findByStudentusername(username);
        if (grades.isEmpty()) return List.of();

        Set<String> teacherUsernames = grades.stream()
                .map(Grade::getTeacherusername)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (teacherUsernames.isEmpty()) {
            return grades.stream()
                    .map(g -> {
                        GradeDTO dto = modelMapper.map(g, GradeDTO.class);
                        dto.setSubjectName(null);
                        return dto;
                    })
                    .toList();
        }

        List<Teacher> teachers = teacherRepository.findByUsernameIn(teacherUsernames);

        Map<String, String> subjectByTeacher = new HashMap<>(teachers.size());
        for (Teacher t : teachers) {
            String subjectName = (t.getSubject() != null) ? t.getSubject().getName() : null;
            subjectByTeacher.put(t.getUsername(), subjectName);
        }

        return grades.stream()
                .map(g -> {
                    GradeDTO dto = modelMapper.map(g, GradeDTO.class);
                    dto.setSubjectName(subjectByTeacher.get(g.getTeacherusername()));
                    return dto;
                })
                .toList();
    }


}
