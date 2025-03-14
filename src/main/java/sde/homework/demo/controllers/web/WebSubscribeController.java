package sde.homework.demo.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import sde.homework.demo.controllers.SubscribeController;
import sde.homework.demo.model.Subscriber;
import sde.homework.demo.repository.SubscriberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/") // 👈 Handles Web Requests
public class WebSubscribeController extends SubscribeController {

    private final SubscriberRepository subscriberRepository;

    @Autowired // ✅ Add this annotation to ensure Spring injects it properly
    public WebSubscribeController(SubscriberRepository subscriberRepository) {
        super(subscriberRepository);
        this.subscriberRepository = subscriberRepository;
    }

    @GetMapping("subscribe/create")
    public String showCreatePage() {
        return "subscribe/create";
    }

    @PostMapping("subscribe/create")
    public String createSubscribe(@RequestParam("email") String email, HttpServletRequest request, Model model) {
        if (subscriberRepository.existsByEmail(email)) {
            model.addAttribute("error", "This email is already subscribed!");
            return "subscribe/create";
        }

        Subscriber subscriber = createSubscriber(email, request, "Web Page");

        // ✅ Fix: Ensure createdAt is set before saving


        subscriberRepository.save(subscriber);
        model.addAttribute("message", "Thank you for subscribing with " + email + "!");
        return "subscribe/create";
    }

    @GetMapping("subscribe/index")
    public String indexSubscribe(Model model) {
        model.addAttribute("subscribers", subscriberRepository.findAll());
        return "subscribe/index";
    }
}
