package com.jaslina.dictionary.repositories;

import com.jaslina.dictionary.models.Dictionary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Bajal on 8/29/2017.
 */
public interface DictionaryRepository extends PagingAndSortingRepository<Dictionary, String>{

    Dictionary findByWord(String word);
}
