package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.AbsenceRepository;
import fon.e_dnevnik.dao.GradeRepository;
import fon.e_dnevnik.dao.StudentRepository;
import fon.e_dnevnik.dto.AbsenceDTO;
import fon.e_dnevnik.dto.ClassDTO;
import fon.e_dnevnik.dto.GradeDTO;
import fon.e_dnevnik.dto.StudentDTO;
import fon.e_dnevnik.entity.Absence;
import fon.e_dnevnik.entity.Grade;
import fon.e_dnevnik.entity.Student;
import fon.e_dnevnik.service.ServiceInterface;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentImplementation implements ServiceInterface<StudentDTO> {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final GradeRepository gradeRepository;
    private final AbsenceRepository absenceRepository;
@Autowired
public StudentImplementation(StudentRepository studentRepository, ModelMapper modelMapper, GradeRepository gradeRepository,
                             AbsenceRepository absenceRepository) {
    this.studentRepository = studentRepository;
    this.modelMapper = modelMapper;
    this.gradeRepository = gradeRepository;
    this.absenceRepository = absenceRepository;
}

@Override
public List<StudentDTO> findAll() {
    List<Student> students = studentRepository.findAll();
    return students.stream()
            .map(student -> modelMapper.map(student, StudentDTO.class))
            .collect(Collectors.toList());
}

@Override
public StudentDTO findById(Object id) throws Exception {
    Optional<Student> student = studentRepository.findById((String) id);
    if (student.isPresent()) {
        return modelMapper.map(student.get(), StudentDTO.class);
    } else {
        throw new Exception("Student not found");
    }
}

@Override
public StudentDTO save(StudentDTO studentDTO) throws Exception {
    Student student = modelMapper.map(studentDTO, Student.class);
    student = studentRepository.save(student);
    return modelMapper.map(student, StudentDTO.class);
}
    public List<GradeDTO> getGrades(String username) {
        List<Grade> grades = gradeRepository.findByStudentUsername(username);
        return grades.stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }

    public List<AbsenceDTO> getAbsences(String username) {
        List<Absence> absences = absenceRepository.findByStudentUsername(username);
        return absences.stream()
                .map(absence -> modelMapper.map(absence, AbsenceDTO.class))
                .collect(Collectors.toList());
    }

public List<StudentDTO> findByStudentClassClassId(int classId) {
    List<Student> students = studentRepository.findByStudentClassClassId(classId);
    return students.stream()
            .map(student -> modelMapper.map(student, StudentDTO.class))
            .collect(Collectors.toList());
}
}
//ovo je druga verzija
//@Service
//public class StudentImplementation implements ServiceInterface<StudentDTO> {
//
//    private StudentRepository studentRepository;
//    private ModelMapper modelMapper;
//    private GradeRepository gradeRepository;
//    private AbsenceRepository absenceRepository;
//
//    @Autowired
//    public StudentImplementation(StudentRepository studentRepository, ModelMapper modelMapper, GradeRepository gradeRepository,
//                                 AbsenceRepository absenceRepository) {
//        this.studentRepository = studentRepository;
//        this.modelMapper = modelMapper;
//        this.gradeRepository = gradeRepository;
//        this.absenceRepository = absenceRepository;
//    }
//
//    @Override
//    public List<StudentDTO> findAll() {
//        List<Student> students = studentRepository.findAll();
//        List<StudentDTO> studentDTOS = new ArrayList<>();
//
//        for (Student student : students) {
//            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
//
//            Hibernate.initialize(student.getGrades());
//
//            // Ručno mapiranje grades
//            List<GradeDTO> gradeDTOS = student.getGrades().stream()
//                    .map(grade -> modelMapper.map(grade, GradeDTO.class))
//                    .collect(Collectors.toList());
//            studentDTO.setGrades(gradeDTOS);
//
//            // Ručno mapiranje absences
//            List<AbsenceDTO> absenceDTOS = student.getAbsences().stream()
//                    .map(absence -> modelMapper.map(absence, AbsenceDTO.class))
//                    .collect(Collectors.toList());
//            studentDTO.setAbsences(absenceDTOS);
//
//            studentDTOS.add(studentDTO);
//        }
//        return studentDTOS;
//    }
//
//    @Override
//    public StudentDTO findById(Object id) throws Exception {
//        Optional<Student> student = studentRepository.findById((String) id);
//        StudentDTO studentDTO;
//        if (student.isPresent()) {
//            studentDTO = modelMapper.map(student.get(), StudentDTO.class);
//            studentDTO.setStudentClass(modelMapper.map(student.get().getStudentClass(), ClassDTO.class));
//
//
//            // Ručno mapiranje grades
////            List<GradeDTO> gradeDTOS = student.get().getGrades().stream()
////                    .map(grade -> modelMapper.map(grade, GradeDTO.class))
////                    .collect(Collectors.toList());
////            studentDTO.setGrades(gradeDTOS);
//
//            // Ručno mapiranje absences
//            List<AbsenceDTO> absenceDTOS = student.get().getAbsences().stream()
//                    .map(absence -> modelMapper.map(absence, AbsenceDTO.class))
//                    .collect(Collectors.toList());
//            studentDTO.setAbsences(absenceDTOS);
//
//            return studentDTO;
//        } else {
//            throw new Exception("Ne postoji ucenik");
//        }
//    }
//
//    @Override
//    public StudentDTO save(StudentDTO studentDTO) throws Exception {
//        return null;
//    }
//
//
//    public List<StudentDTO> findByStudentClassClassId(int classId) {
//        System.out.println("1");
//        List<Student> students = studentRepository.findByStudentClassClassId(classId);
//        List<StudentDTO> studentDTOS = new ArrayList<>();
//        for (Student student : students) {
//            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
//            System.out.println("2");
//
//            Hibernate.initialize(student.getGrades());
//            // Ručno mapiranje grades
//            List<GradeDTO> gradeDTOS = student.getGrades().stream()
//                    .map(grade -> modelMapper.map(grade, GradeDTO.class))
//                    .collect(Collectors.toList());
//            studentDTO.setGrades(gradeDTOS);
//            System.out.println("3");
//            // Ručno mapiranje absences
//            List<AbsenceDTO> absenceDTOS = student.getAbsences().stream()
//                    .map(absence -> modelMapper.map(absence, AbsenceDTO.class))
//                    .collect(Collectors.toList());
//            studentDTO.setAbsences(absenceDTOS);
//
//            studentDTOS.add(studentDTO);
//        }
//        return studentDTOS;
//    }
//}

//ovo je prva verzija
//@Service
//public class StudentImplementation implements ServiceInterface<StudentDTO> {
//
//    private StudentRepository studentRepository;
//    private ModelMapper modelMapper;
//
//    private GradeRepository gradeRepository;
//
//    private AbsenceRepository absenceRepository;
//
//    @Autowired
//    public StudentImplementation(StudentRepository studentRepository, ModelMapper modelMapper, GradeRepository gradeRepository,
//                                 AbsenceRepository absenceRepository) {
//        this.studentRepository = studentRepository;
//        this.modelMapper = modelMapper;
//        this.gradeRepository=gradeRepository;
//        this.absenceRepository=absenceRepository;
//    }
//
//    @Override
//    public List<StudentDTO> findAll() {
//        List<Student> students = studentRepository.findAll();
//        List<StudentDTO> studentDTOS = new ArrayList<>();
//
//        for(Student student: students){
//            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
//           //studentDTO.setStudentClassDTO(modelMapper.map(student.getStudentClass(), Class.class));
//            studentDTOS.add(studentDTO);
//        }
//        return studentDTOS;
//    }
//
//    @Override
//    public StudentDTO findById(Object id) throws Exception {
//        Optional<Student> student=studentRepository.findById((String) id);
//        StudentDTO studentDTO;
//        if(student.isPresent()) {
//            studentDTO = modelMapper.map(student.get(), StudentDTO.class);
//            studentDTO.setStudentClass(modelMapper.map(student.get().getStudentClass(), ClassDTO.class));
//          //  studentDTO.setAbsences(getAbsences(student.get().getUsername()));
//          //  studentDTO.setGrades(getGrades(student.get().getUsername()));
//            return studentDTO;
//        }
//        else{
//            throw new Exception("Ne postoji ucenik");
//        }
//    }
//
//    @Override
//    public StudentDTO save(StudentDTO studentDTO) throws Exception {
//        return null;
//    }
//
//    public List<GradeDTO> getGrades(String username) {
//        List<Grade> grades = gradeRepository.findByStudentUsername(username);
//        List<GradeDTO> gradeDTOS = new ArrayList<>();
//        for(Grade g: grades){
//            GradeDTO gradeDTO = modelMapper.map(g, GradeDTO.class);
//            gradeDTOS.add(gradeDTO);
//        }
//        return gradeDTOS;
//    }
//
//    public List<AbsenceDTO> getAbsences(String username) {
//        List<Absence> absences = absenceRepository.findByStudentUsername(username);
//        List<AbsenceDTO> absenceDTOS = new ArrayList<>();
//        for(Absence g: absences){
//            AbsenceDTO absenceDTO = modelMapper.map(g, AbsenceDTO.class);
//            absenceDTOS.add(absenceDTO);
//        }
//        return absenceDTOS;
//
//    }
//
//    public List<StudentDTO> findByStudentClassClassId(int classId){
//        List<Student> students = studentRepository.findByStudentClassClassId(classId);
//        List<StudentDTO> studentDTOS = new ArrayList<>();
//        for(Student student: students){
//                StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
//                System.out.println("da li ovde dodje");
//                studentDTOS.add(studentDTO);
//        }
//        return studentDTOS;
//    }
//}

