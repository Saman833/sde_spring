package sde.homework.demo.controllers;

import sde.homework.demo.model.Subscriber;
import sde.homework.demo.repository.SubscriberRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public abstract class SubscribeController {
    protected final SubscriberRepository subscriberRepository;

    public SubscribeController(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    protected Subscriber createSubscriber(String email, HttpServletRequest request, String source) {
        String ipAddress = request.getRemoteAddr();
        return new Subscriber(email, LocalDateTime.now(), ipAddress, source);
    }
}
