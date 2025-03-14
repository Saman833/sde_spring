package sde.homework.demo.githubmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sde.homework.demo.githubmanager.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

