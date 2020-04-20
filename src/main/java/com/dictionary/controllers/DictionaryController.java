package com.dictionary.controllers;


import com.dictionary.models.Dictionary;
import com.dictionary.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DictionaryController {

    @Autowired
    public DictionaryService dictionaryService;


    @RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String searchMeaning(@RequestParam(name="word")  String word){

        List<String> search = dictionaryService.search(word);
        return search.toString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addWordAndMeaning(@RequestBody Dictionary dictionaryEntry){

        dictionaryService.add(dictionaryEntry);
    }



  //  @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
  //  @ResponseBody
  //  public List<String> searchAutocomplete(@RequestParam(name="word")  String word){
  //      List<String> search = dictionaryService.searchAutocomplete(word);
  //      return search;
   // }


   /// @CrossOrigin(origins = "http://localhost:8080")
   // @RequestMapping(value = "/autocomplete", method = RequestMethod.GET, produces = "application/json")
   // @ResponseBody
   // public List<String> searchAutocomplete(@RequestParam(name="word", required = false, defaultValue = "")  String word, HttpServletResponse response){
   //     System.out.println("searchstr: " + word);
   //     response.setStatus(HttpServletResponse.SC_OK);
      //  List<String> search = null;
      //  if (word.length() == 1) {
      //      search = dictionaryService.searchAutocomplete(word);
      //  }
      //  response.setContentType("application/json");
       // return search;
   // }






}
