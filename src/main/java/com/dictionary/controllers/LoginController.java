package com.dictionary.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by home on 4/9/2020.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String showRegistrationForm(Model model) {
        return "login";
    }

    private boolean isRememberMeAuthenticated() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }



    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @PostMapping("/user")
    public String login(@RequestParam(name="error",required=false) String error,
                        ModelMap model) {

        if(error != null) {
            model.put("credentialsError", "Invalid credentials");
            return "login";
        }

        return "redirect:/";
    }

}
