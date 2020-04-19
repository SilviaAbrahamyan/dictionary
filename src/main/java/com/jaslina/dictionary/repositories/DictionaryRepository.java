package com.jaslina.dictionary.repositories;

//import com.jaslina.dictionary.models.Dictionary;
import com.jaslina.dictionary.models.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, String> {


    List<Dictionary> findByWord(String word);

    List<Dictionary> findByWordLike(@Param("word") String word);

}
