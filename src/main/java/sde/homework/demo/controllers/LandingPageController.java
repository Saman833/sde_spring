package sde.homework.demo.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sde.homework.demo.model.Subscriber;
import sde.homework.demo.repository.SubscriberRepository;

import java.time.LocalDateTime;

@Controller
public class LandingPageController {
    private final SubscriberRepository subscriberRepository;
    public LandingPageController(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }
    // Page for subscribing
    @GetMapping("/")
    public String showSubscriptionPage() {
        return "index";
    }

    // Page for listing subscribers
    @GetMapping("/subscribe/index")
    public String indexSubscribe(Model model) {
        model.addAttribute("subscribers", subscriberRepository.findAll());
        return "subscribe/index";
    }

    // Handle email subscription
    @GetMapping("/subscribe/create")
    public String showCreatePage() {
        return "subscribe/create"; // Loads create.html
    }

    // Handle form submission (POST request)
    @PostMapping("/subscribe/create")
    public String createSubscribe(@RequestParam("email") String email, HttpServletRequest request, Model model) {
        if (subscriberRepository.existsByEmail(email)) {
            model.addAttribute("error", "This email is already subscribed!");
            return "subscribe/create";
        }

        String ipAddress = request.getRemoteAddr();
        Subscriber subscriber = new Subscriber(email, LocalDateTime.now(), ipAddress);
        subscriberRepository.save(subscriber);

        model.addAttribute("message", "Thank you for subscribing with " + email + "!");
        return "subscribe/create";
    }
}




