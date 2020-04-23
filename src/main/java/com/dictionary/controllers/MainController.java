package com.dictionary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String root() {
        return "home";
    }

    @GetMapping("/home")
    public String login(Model model) {
        return "home";
    }


}