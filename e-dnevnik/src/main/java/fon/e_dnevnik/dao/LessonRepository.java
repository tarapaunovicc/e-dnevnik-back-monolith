package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findByLessonidIn(Collection<Integer> ids);
}
