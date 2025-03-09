package sde.homework.demo.repository;

import sde.homework.demo.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    public boolean existsByEmail(String email);

    Optional<Subscriber> findByEmail(String email);
}
