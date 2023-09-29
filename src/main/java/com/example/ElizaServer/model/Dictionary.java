package com.example.ElizaServer.model;

import java.util.List;

public class Dictionary {
    private String dictionary_name;
    private List<DictionaryEntry> entries;

    public String getDictionary_name() {
        return dictionary_name;
    }

    public void setDictionary_name(String dictionary_name) {
        this.dictionary_name = dictionary_name;
    }

    public List<DictionaryEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<DictionaryEntry> entries) {
        this.entries = entries;
    }
}
