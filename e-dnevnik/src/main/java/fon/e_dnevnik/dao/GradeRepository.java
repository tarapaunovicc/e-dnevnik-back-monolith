package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    List<Grade> findByStudentusername(String username);
    List<Grade> findByStudentusernameAndTeacherusername(String studentUsername, String teacherUsername);

}
