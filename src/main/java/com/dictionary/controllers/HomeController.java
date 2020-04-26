package com.dictionary.controllers;

import com.dictionary.models.User;
import com.dictionary.models.Words;
import com.dictionary.services.DictionaryService;
import com.dictionary.services.WordsService;
import com.dictionary.util.ClientFormWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 4/11/2020.
 */

@Controller
public class HomeController {
    List<String> search;
    String s;
    String username ;
    @Autowired
    public DictionaryService dictionaryService;

    @Autowired
    public WordsService wordsService;

    @RequestMapping(value = "/personalpage")
    public ModelAndView showRegistrationForm(@RequestParam(name = "userName") String userName) {
        userName = userName;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", userName);
        modelAndView.setViewName("personalpage");
        return modelAndView;
    }


    @RequestMapping(value = "/searching", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String search(@RequestParam(name = "word", defaultValue = "") String word, ModelMap modelMap) {
        if (!(word.equals(""))) {
            s = word;
            ModelAndView modelAndView = new ModelAndView();
            modelMap.addAttribute("word", word);
            modelMap.addAttribute("response", "success");
            modelMap.addAttribute("survey", search);
            return "redirect:/search1";
        }
        return "redirect:/searchpage?success";
    }

    @RequestMapping(value = "/search1")
    public ModelAndView searching(@RequestParam(name = "word") String word) {

        if (s.equals(word)) {
            s = word;
            ModelAndView modelAndView = new ModelAndView();
            List<String> search = dictionaryService.search(word);
            modelAndView.addObject("response", "success");
            modelAndView.addObject("word", word);
            modelAndView.addObject("survey", search);
            modelAndView.setViewName("searchpage");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        List<String> search = dictionaryService.search(s);
        modelAndView.addObject("response", "success");
        modelAndView.addObject("resp", "edited");
        modelAndView.addObject("word", word);
        modelAndView.addObject("survey", search);
        modelAndView.setViewName("searchpage");
        return modelAndView;
    }

    @GetMapping("/searchpage")
    public String searchpage() {
        return "searchpage";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String yourMethod(@RequestParam(name = "notes") String[] notes) {
        int i = 0;
        List<String> stringList = new ArrayList<>();
        List<String> search = dictionaryService.search(s);
        for (String data : notes) {
            stringList.add(data);
            System.out.println("Your Data =>" + data);
          //  Words words = new Words(s, data, ++i);
          //  wordsService.add(words);
        }
        boolean found = false;
        for(String object1 : stringList){
            for(String object2: search){
                if(object1.replaceAll("<br>", "").trim().equals(object2.trim())){
                    found = true;
                    //also do something
                    break;
                }
            }
            if(!found){
                Words words = new Words(s, object1, ++i);
                  wordsService.add(words);
            }
            found = false;
        }


        return "redirect:/search1?word=" + s;
    }


    @RequestMapping(value = "/editing")
    public ModelAndView editform() {
        System.out.println(wordsService.findall().toString());
        ClientFormWrapper clientFormWrapper = new ClientFormWrapper();
        clientFormWrapper.setClientList(wordsService.findall());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("respone", clientFormWrapper.getClientList());
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @RequestMapping(value = "/wordedite/{serviceId}/{wordId}", method = RequestMethod.GET)
    public String wordEdit(@PathVariable int serviceId, @PathVariable  String wordId) {
        System.out.println(serviceId);
        String meaing = wordsService.findbyWordAndType(wordId, serviceId);
        dictionaryService.update(meaing, wordId, serviceId);
        wordsService.delete(wordId,serviceId);
        return "redirect:/editing";
    }

}
