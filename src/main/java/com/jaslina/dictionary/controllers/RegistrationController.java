package com.jaslina.dictionary.controllers;


import com.jaslina.dictionary.models.User;
import com.jaslina.dictionary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by home on 4/9/2020.
 */
@Controller
// @RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    // Return registration form template
    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }



    // Process form input data
    @RequestMapping(value = "/registration/user", method = RequestMethod.POST)
    public String registerUserAccount(ModelMap model, @ModelAttribute("/registration/user") @Valid User user,
                                   BindingResult bindingResult){

        int loginSize = userService.findUserByUsername(user.getUsername()).size();

        if (loginSize > 0) {
            bindingResult.rejectValue("username", "error.username",
                    "This user name already exists");
        }

        int emailSize = userService.findUserByEmail(user.getEmail()).size();

        if (emailSize > 0) {
            bindingResult.rejectValue("email", "error.email",
                    "This email already exists");
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "error.passwordConfirm",
                    "Password confirmation does not match to password");
        }

        if (bindingResult.hasErrors()) {

            return "registration";
        }

        userService.saveUser(user, true);

        model.addAttribute("user", new User());
        model.put("successMessage", "User has been registered successfully");

        //return "redirect:/registration?success";
        return "redirect:/login";
    }
}
