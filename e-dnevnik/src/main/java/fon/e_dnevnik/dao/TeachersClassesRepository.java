package fon.e_dnevnik.dao;


import fon.e_dnevnik.entity.TeachersClasses;
import fon.e_dnevnik.entity.primarykey.TeachersClassesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachersClassesRepository extends JpaRepository<TeachersClasses, TeachersClassesPK> {
    List<TeachersClasses> findByTeacherUsername(String username);
    TeachersClasses findByIdClassidAndIdTeacherusername(Integer classid, String username);
}
