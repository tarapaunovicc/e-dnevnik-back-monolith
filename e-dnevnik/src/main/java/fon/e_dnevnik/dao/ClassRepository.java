package fon.e_dnevnik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fon.e_dnevnik.entity.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    Class findByClassTeacherUsername (String username);
}
