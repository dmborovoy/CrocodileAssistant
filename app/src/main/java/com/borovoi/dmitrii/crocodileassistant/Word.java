package com.borovoi.dmitrii.crocodileassistant;


/**
 * Created by dimas on 11/2/2015.
 */
public class Word {
    private Long id;
    private String word;
    private boolean isSingleWord;
    private Level level;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isSingleWord() {
        return isSingleWord;
    }

    public void setIsSingleWord(boolean isSingleWord) {
        this.isSingleWord = isSingleWord;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", isSingleWord=" + isSingleWord +
                ", level=" + level +
                '}';
    }
}
