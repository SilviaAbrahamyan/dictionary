package com.dictionary.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "words")
public class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "word")
    private String word;

    @Column(name = "meaning")
    private String meaning;


    public Words() {

    }

    public Words(String word, String mean, int type) {
        this.word = word;
        this.meaning = mean;
        this.type = type;
    }

    @Column(name = "type")
    private int type;

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

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", mean='" + meaning + '\'' +
                ", type=" + type +
                '}';
    }
}