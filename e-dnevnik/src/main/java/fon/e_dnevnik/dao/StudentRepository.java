package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByStudentClass(int classId);
}
