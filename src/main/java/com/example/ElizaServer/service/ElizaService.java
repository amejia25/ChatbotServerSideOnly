package com.example.ElizaServer.service;
import java.util.Collections;
import java.util.ArrayList;

import com.example.ElizaServer.model.Dictionary;
import com.example.ElizaServer.model.DictionaryEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Random;

@Service
public class ElizaService {

    private Dictionary dictionary;
    
    // Default responses
    private final String[] defaultResponses = {
        "Can you elaborate on that?",
        "Why do you say that?",
        "I see. How does that make you feel?"
    };

    public ElizaService() {
        // Load the dictionary from the JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/default.json")) {
            dictionary = objectMapper.readValue(is, Dictionary.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String userInput, List<Integer> usedAnswers, List<Integer> usedQuestions) {
        Random random = new Random();
        // Shuffle the dictionary entries for random order of keyword checking
        List<DictionaryEntry> shuffledEntries = new ArrayList<>(dictionary.getEntries());
        Collections.shuffle(shuffledEntries);

        for (DictionaryEntry entry : shuffledEntries) {
            // Shuffle the order of keywords for each entry
            List<String> keys = new ArrayList<>(entry.getKey());
            Collections.shuffle(keys);
            
            for (String key : keys) {
                if (userInput.toLowerCase().contains(key.toLowerCase())) {

                    int answerIndex;
                    do {
                        answerIndex = random.nextInt(entry.getAnswer().size());
                    } while (usedAnswers.contains(answerIndex));
                    usedAnswers.add(answerIndex);
    
                    // Ensure the question hasn't been used before
                    int questionIndex;
                    do {
                        questionIndex = random.nextInt(entry.getQuestion().size());
                    } while (usedQuestions.contains(questionIndex));
                    usedQuestions.add(questionIndex);
    
                    String answer = entry.getAnswer().get(answerIndex);
                    String question = entry.getQuestion().get(questionIndex);

                     // Check if all answers or questions have been used and reset if necessary
                    if (usedAnswers.size() == entry.getAnswer().size()) {
                    usedAnswers.clear();
                    }
                    if (usedQuestions.size() == entry.getQuestion().size()) {
                    usedQuestions.clear();
                    }
     
                    // Randomize the response format
                    int formatChoice = random.nextInt(3);
                    switch (formatChoice) {
                        case 0:
                            return answer;
                        case 1:
                            return question;
                        case 2:
                        default:
                            return answer + " " + question;
                    }
                }
            }
        }

        // If no keyword is matched, return a default response
        int index = new Random().nextInt(defaultResponses.length);
        return defaultResponses[index];
    }
}

