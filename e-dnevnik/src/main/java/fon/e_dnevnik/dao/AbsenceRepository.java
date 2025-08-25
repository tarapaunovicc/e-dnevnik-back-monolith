package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Absence;
import fon.e_dnevnik.entity.primarykey.AbsencePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, AbsencePK> {
    @Query("SELECT a FROM Absence a " +
            "JOIN FETCH a.lesson l " +
            "JOIN FETCH a.student s " +
            "WHERE a.id.studentusername = :username")
    List<Absence> findByStudentUsername(@Param("username") String username);
}
