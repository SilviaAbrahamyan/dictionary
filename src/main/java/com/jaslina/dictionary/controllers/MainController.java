package com.jaslina.dictionary.controllers;

/**
 * Created by home on 3/7/2020.
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {





    @GetMapping("/")
    public String root() {
        return "search";
    }

    @GetMapping("/search")
    public String login(Model model) {
        return "search";
    }

}