package com.example.dictionary.model;

public class Model {
    int id;
    String word;
    String translate;
    int favourite;
    int translate_id;

    public Model(int id, String word, String translate,int favourite, int translate_id) {
        this.id = id;
        this.word = word;
        this.translate = translate;
        this.favourite = favourite;
        this.translate_id = translate_id;
    }

    public Model(String word, String translate,int favourite, int translate_id) {
        this.word = word;
        this.translate = translate;
        this.favourite = favourite;
        this.translate_id = translate_id;
    }

    public Model(int favourite) {
        this.favourite = favourite;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public int getTranslate_id() {
        return translate_id;
    }

    public void setTranslate_id(int translate_id) {
        this.translate_id = translate_id;
    }
}
