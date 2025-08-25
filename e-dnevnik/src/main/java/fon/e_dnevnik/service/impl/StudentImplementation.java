package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.AbsenceRepository;
import fon.e_dnevnik.dao.GradeRepository;
import fon.e_dnevnik.dao.StudentRepository;
import fon.e_dnevnik.dto.StudentDTO;
import fon.e_dnevnik.entity.Student;
import fon.e_dnevnik.service.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentImplementation implements ServiceInterface<StudentDTO> {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
@Autowired
public StudentImplementation(StudentRepository studentRepository, ModelMapper modelMapper, GradeRepository gradeRepository,
                             AbsenceRepository absenceRepository) {
    this.studentRepository = studentRepository;
    this.modelMapper = modelMapper;
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
public List<StudentDTO> findByStudentClassClassId(int classId) {
    List<Student> students = studentRepository.findByStudentClassClassId(classId);
    return students.stream()
            .map(student -> modelMapper.map(student, StudentDTO.class))
            .collect(Collectors.toList());
}
}
