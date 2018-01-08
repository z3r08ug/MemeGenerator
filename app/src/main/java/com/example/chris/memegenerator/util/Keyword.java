package com.example.chris.memegenerator.util;

/**
 * Created by Admin on 1/8/2018.
 */

public class Keyword {
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getGrammar() {
        return grammar;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    String word;
    String grammar;

    @Override
    public String toString() {
        return word + " meme";
    }
}
