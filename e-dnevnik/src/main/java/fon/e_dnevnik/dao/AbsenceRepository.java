package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Absence;
import fon.e_dnevnik.entity.primarykey.AbsencePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, AbsencePK> {
    List<Absence> findByIdStudentusername(String username);
}
