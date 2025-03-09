package sde.homework.demo.controllers.api;

import sde.homework.demo.controllers.SubscribeController;
import sde.homework.demo.model.Subscriber;
import sde.homework.demo.repository.SubscriberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api") // 👈 Handles API Requests
public class ApiSubscribeController extends SubscribeController {

    public ApiSubscribeController(SubscriberRepository subscriberRepository) {
        super(subscriberRepository);
    }
    private SubscriberRepository subscriberRepository;
    // Handle API-based subscription
    @PostMapping("/subscribe/create")
    public ResponseEntity<Object> subscribe(@RequestBody Map<String, String> payload, HttpServletRequest request) {
        String email = payload.get("email");

        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Email is required"));
        }

        if (subscriberRepository.existsByEmail(email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "This email is already subscribed!"));
        }

        Subscriber subscriber = createSubscriber(email, request, "API");
        subscriberRepository.save(subscriber);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Subscription successful!", "email", email));
    }
    @GetMapping("/subscribe/index")
    public ResponseEntity<Object> getSubscribers() {
        List<Subscriber> subscribers = subscriberRepository.findAll();

        return ResponseEntity.ok(Map.of("count", subscribers.size(), "subscribers", subscribers));
    }
}

