package com.jaslina.dictionary.controllers;

import com.jaslina.dictionary.models.Dictionary;
import com.jaslina.dictionary.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bajal on 8/29/2017.
 */
@RestController
public class DictionaryController {

    @Autowired
    public DictionaryService dictionaryService;

    @RequestMapping("/search")
    public List<String> searchMeaning(@RequestParam String word){

        return dictionaryService.search(word);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addWordAndMeaning(@RequestBody Dictionary dictionaryEntry){

        dictionaryService.add(dictionaryEntry);
    }






}
