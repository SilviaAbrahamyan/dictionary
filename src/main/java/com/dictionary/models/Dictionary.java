package com.dictionary.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "dictionary")
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;


    @Column(name = "word")
    private String word;

    @Column(name = "meaning")
    private String mean;

    @Column(name = "type")
    private int type;

   // public List<String> meaning;

    public Dictionary() {

    }

    public Dictionary(String word) {
        this.word = word;
    }

    public Dictionary(String word, String mean, int type) {
        this.word = word;
        this.mean = mean;
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
