package com.example.ElizaServer.controller;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.ElizaServer.service.ElizaService;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Random;

@Controller
@SessionAttributes({"name", "conversationHistory", "usedAnswers", "usedQuestions"})
public class ElizaController {

    @Autowired
    private ElizaService elizaService;

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
    public String setName(@RequestParam String name, Model model,
                          @ModelAttribute("conversationHistory") List<String> conversationHistory) {
        model.addAttribute("name", name);
        
        // Generate a greeting for the user
        String greeting = generateGreeting(name);
        
        // Add the greeting to the conversation history
        conversationHistory.add("Eliza: " + greeting);
        
        model.addAttribute("conversationHistory", conversationHistory);
        
        return "chat";
    }
    

    private String generateGreeting(String name) {
        int index = new Random().nextInt(greetings.length);
        return name + ", " + greetings[index];
    }

    @ModelAttribute("conversationHistory")
    public List<String> conversationHistory() {
        return new ArrayList<>();
    }

    @PostMapping("/chat")
    public String chat(@RequestParam String userMessage, Model model, 
                       @ModelAttribute("name") String name,
                       @ModelAttribute("conversationHistory") List<String> conversationHistory,
                       @ModelAttribute("usedAnswers") List<Integer> usedAnswers,
                       @ModelAttribute("usedQuestions") List<Integer> usedQuestions) {
        if (name == null || name.isEmpty()) {
            return "greet";
        }
        String elizaResponse = elizaService.getResponse(userMessage, usedAnswers, usedQuestions);
        
        // Add user's message and Eliza's response to the conversation history
        conversationHistory.add(name + ": " + userMessage);
        conversationHistory.add("Eliza: " + elizaResponse);
        
        model.addAttribute("conversationHistory", conversationHistory);
        return "chat";
    }

    @ModelAttribute("usedAnswers")
    public List<Integer> usedAnswers() {
    return new ArrayList<>();
    }

    @ModelAttribute("usedQuestions")
    public List<Integer> usedQuestions() {
    return new ArrayList<>();
    } 
}
