package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Student;
import fon.e_dnevnik.entity.Teacher;
import fon.e_dnevnik.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    Teacher findTeacherByUserTeacher(User user);
    Teacher findTeacherByUserTeacherUsername(String username);
}
