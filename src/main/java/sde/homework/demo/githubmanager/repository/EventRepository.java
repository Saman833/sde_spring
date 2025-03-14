package sde.homework.demo.githubmanager.repository;



import org.springframework.stereotype.Repository;
import sde.homework.demo.githubmanager.model.GitEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<GitEvent, Long> {
    List<GitEvent> findByRepository(String repository);
}

