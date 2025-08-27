package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    @Query("select t from Teacher t left join fetch t.subject where t.username in :usernames")
    List<Teacher> findByUsernameIn(@Param("usernames") Collection<String> usernames);
}