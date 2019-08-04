package com.laurikosonen.jobtitlegenerator;

public class Word {
    public String word;
    public int categoryId;

    public Word(String word,
                int categoryId) {
        this.word = word;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return word;
    }
}
