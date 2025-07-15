package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Grade;
import fon.e_dnevnik.entity.primarykey.GradePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradePK> {
    List<Grade> findByStudentUsername(String username);
}
