package sde.homework.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {

    @GetMapping("/")
    public String helloWorld() {
        return "index"; // This will return the index.html page from templates (Thymeleaf) or static (if plain HTML)
    }
}

