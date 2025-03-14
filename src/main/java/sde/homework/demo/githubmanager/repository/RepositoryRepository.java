package sde.homework.demo.githubmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sde.homework.demo.githubmanager.model.GitRepository;

@Repository
public interface RepositoryRepository extends JpaRepository<GitRepository, Long> {
     public boolean existsByGithubRepoUrl(String githubRepoUrl);
}

