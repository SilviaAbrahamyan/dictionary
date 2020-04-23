package com.dictionary.controllers;


import com.dictionary.models.Dictionary;
import com.dictionary.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;


@RestController
public class DictionaryController {

    @Autowired
    public DictionaryService dictionaryService;



    @RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView searchMeaning(@RequestParam(name="word")  String word){
        List<String> search = dictionaryService.search(word);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("word", word);
        modelAndView.addObject("survey", search);
        modelAndView.addObject("response", "success1");
        modelAndView.setViewName("searchpage");
        return modelAndView;
    }



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addWordAndMeaning(@RequestBody Dictionary dictionaryEntry){

        dictionaryService.add(dictionaryEntry);
    }


}
