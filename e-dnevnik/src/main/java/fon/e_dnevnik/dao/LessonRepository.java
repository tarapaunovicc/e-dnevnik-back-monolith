package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Lesson;
import fon.e_dnevnik.entity.primarykey.LessonPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, LessonPK> {
}
