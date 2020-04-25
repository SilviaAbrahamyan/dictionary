package com.dictionary.controllers;

import com.dictionary.models.User;
import com.dictionary.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by home on 4/11/2020.
 */

@Controller
public class HomeController {
    List<String> search;
    String s;
    @Autowired
    public DictionaryService dictionaryService;

    @RequestMapping(value = "/personalpage")
    public ModelAndView showRegistrationForm(@RequestParam(name = "userName") String userName) {
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
        for (String data : notes) {
            System.out.println("Your Data =>" + data);
        }
        return "redirect:/search1?word=" + s;
    }


}
