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

    public ElizaService() {
        // Load the dictionary from the JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/default.json")) {
            dictionary = objectMapper.readValue(is, Dictionary.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String userInput) {
        // Shuffle the dictionary entries for random order of keyword checking
        List<DictionaryEntry> shuffledEntries = new ArrayList<>(dictionary.getEntries());
        Collections.shuffle(shuffledEntries);

        for (DictionaryEntry entry : shuffledEntries) {
            // Shuffle the order of keywords for each entry
            List<String> keys = new ArrayList<>(entry.getKey());
            Collections.shuffle(keys);
            
            for (String key : keys) {
                if (userInput.toLowerCase().contains(key.toLowerCase())) {
                    Random random = new Random();
                    String answer = entry.getAnswer().get(random.nextInt(entry.getAnswer().size()));
                    String question = entry.getQuestion().get(random.nextInt(entry.getQuestion().size()));

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
        return "I'm not sure how to respond to that.";
    }
}
