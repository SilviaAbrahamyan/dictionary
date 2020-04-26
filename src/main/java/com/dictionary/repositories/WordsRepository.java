package com.dictionary.repositories;

import com.dictionary.models.Dictionary;
import com.dictionary.models.Words;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wordsRepository")
public interface WordsRepository extends JpaRepository<Words, String> {

   Words findByWordAndType(String word, int type);
    long deleteById(int id);

}
