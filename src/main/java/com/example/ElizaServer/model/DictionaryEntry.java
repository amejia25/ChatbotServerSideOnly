package com.example.ElizaServer.model;

import java.util.List;

public class DictionaryEntry {
    private List<String> key;
    private List<String> answer;
    private List<String> question;

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public List<String> getQuestion() {
        return question;
    }

    public void setQuestion(List<String> question) {
        this.question = question;
    }
}
