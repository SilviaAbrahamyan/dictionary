package com.dictionary.services;

import com.dictionary.models.Words;
import com.dictionary.repositories.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("wordsService")
public class WordsService {

    @Autowired
    private WordsRepository wordsRepository;

    public void add(Words words) {
        wordsRepository.save(words);
    }

    public List<Words> findall(){
        return wordsRepository.findAll();
    }

    public String findbyWordAndType(String word, int type) {

        Words wordaAndType = wordsRepository.findByWordAndType(word, type);
        return wordaAndType.getMeaning();
    }
    @Transactional
    public void delete (String word, int type){
        Words wordaAndType = wordsRepository.findByWordAndType(word, type);
        wordsRepository.deleteById(wordaAndType.getId());
    }
}
