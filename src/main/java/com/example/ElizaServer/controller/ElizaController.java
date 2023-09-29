package com.example.ElizaServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Random;

@Controller
@SessionAttributes("name")
public class ElizaController {

    private final String[] greetings = {
        "how is your day going?",
        "is something troubling you?",
        "you seem happy, why is that?"
    };
 
    @GetMapping("/")
    public String greetUser(Model model) {
        if (model.containsAttribute("name")) {
            model.addAttribute("elizaMessage", generateGreeting((String) model.getAttribute("name")));
            return "chat";
        }
        return "greet";
    }

    @PostMapping("/setName")
    public String setName(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("elizaMessage", generateGreeting(name));
        return "chat";
    }

    private String generateGreeting(String name) {
        int index = new Random().nextInt(greetings.length);
        return name + ", " + greetings[index];
    }
}
