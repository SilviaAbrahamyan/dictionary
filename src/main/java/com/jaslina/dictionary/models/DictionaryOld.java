//package com.jaslina.dictionary.models;
//
//import java.util.List;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Table;
//
///**
// * Created by Bajal on 8/29/2017.
// */
//
//@Document(collection = "dictionary")
//public class Dictionary {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Field("id")
//    private String id;
//
//    @Field("word")
//    public String word;
//
//    public List<String> meaning;
//
//    public String getWord() {
//        return word;
//    }
//
//    public void setWord(String word) {
//        this.word = word;
//    }
//
//    public List<String> getMeaning() {
//        return meaning;
//    }
//
//    public void setMeaning(List<String> meaning) {
//        this.meaning = meaning;
//    }
//}
