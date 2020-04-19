package com.jaslina.dictionary.controllers;

import com.jaslina.dictionary.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class AutocompleteController {



  @Autowired
  public DictionaryService dictionaryService;

  /**
   * the rest controller which is requested by the autocomplete input field
   * instead of the countries here could also be made a DB request
   */



    @RequestMapping(value = "/suggestion", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
  public List<String> searchAutocomplete(@RequestParam(name="word")  String word){
      System.out.println("searchstr: " + word);
    List<String> search = dictionaryService.searchAutocomplete(word);
       return search;
  }
}
