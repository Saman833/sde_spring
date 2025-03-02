package sde.homework.demo.repository;



import sde.homework.demo.model.Subscriber;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubscriberRepository {
    private final List<Subscriber> subscribers = new ArrayList<>();

    public void save(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public List<Subscriber> findAll() {
        return subscribers;
    }

    public boolean existsByEmail(String email) {
        return subscribers.stream().anyMatch(subscriber -> subscriber.getEmail().equalsIgnoreCase(email));
    }
}