package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Student;
import fon.e_dnevnik.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByStudentClassClassId(int classId);
    Student findStudentByUserStudent(User user);
    Student findStudentByUserStudentUsername(String username);
}
