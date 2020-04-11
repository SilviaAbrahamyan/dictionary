package com.jaslina.dictionary.services;

import com.jaslina.dictionary.models.Dictionary;
import com.jaslina.dictionary.repositories.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Bajal on 8/29/2017.
 */

@Service
public class DictionaryService {

    public List<Dictionary> dictionary;

    @Autowired
    DictionaryRepository dictionaryRepository;

    public List<String> search(String word) {

        Dictionary d = dictionaryRepository.findByWord(word);
        return d==null?null:d.getMeaning();
    }


    public void add(Dictionary dictionaryEntry) {
        dictionaryRepository.save(dictionaryEntry);
    }
}
