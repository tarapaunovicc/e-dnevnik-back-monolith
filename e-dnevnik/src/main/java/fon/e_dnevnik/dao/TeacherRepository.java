package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    Teacher findTeacherByUserTeacherUsername(String username);
}
