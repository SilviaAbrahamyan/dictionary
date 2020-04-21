package com.dictionary.controllers;

        import org.springframework.http.MediaType;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.servlet.ModelAndView;

        import java.util.List;

/**
 * Created by home on 4/11/2020.
 */

@Controller
public class HomeController {



        @RequestMapping(value = "/personalpage")
        public ModelAndView showRegistrationForm(@RequestParam(name="userName")  String userName){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("userName", userName);
                modelAndView.setViewName("personalpage");
                return modelAndView;
        }
}
