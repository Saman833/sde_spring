package sde.homework.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LandingPageController {

    @GetMapping("/")
    public String helloWorld() {
        return "index"; // This will return the index.html page from templates (Thymeleaf) or static (if plain HTML)
    }
    @PostMapping("/subscribe")
    public String subscribe(@RequestParam("email") String email, Model model) {
        model.addAttribute("message", "Thank you for subscribing with " + email + "!");
        return "index"; // Redirects back to the landing page with a success message
    }
}

