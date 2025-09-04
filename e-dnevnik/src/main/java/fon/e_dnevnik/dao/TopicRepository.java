package fon.e_dnevnik.dao;

import fon.e_dnevnik.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    List<Topic> findBySubjectidOrderByNameAsc(Integer subjectid);
}