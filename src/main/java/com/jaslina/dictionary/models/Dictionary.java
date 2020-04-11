package com.jaslina.dictionary.models;

import com.sun.javafx.beans.IDProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Bajal on 8/29/2017.
 */

@Document
public class Dictionary {

    @Id
    private String _id;

    public String word;
    public List<String> meaning;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getMeaning() {
        return meaning;
    }

    public void setMeaning(List<String> meaning) {
        this.meaning = meaning;
    }



}
