package com.search.autocomplete.utils;

public class MatchedItem {
    private int id;
    private String word;

    public MatchedItem(int id, String word) {
        this.id = id;
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }
}
