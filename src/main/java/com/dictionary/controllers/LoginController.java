package com.dictionary.controllers;

import com.dictionary.models.User;
import com.dictionary.services.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

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
                        @RequestParam(name="username") String username,
                        @RequestParam(name="password") String password,
                        ModelMap model) {

        List<User> loginSize = userService.findUserByUsername(username);
        for(User user: loginSize){
            String encode = new BCryptPasswordEncoder().encode(password);
            if(user.getPassword().equals(password)){
                model.addAttribute("userName", user.getUsername());
                return "redirect:/personalpage";

            }
        }
        return "redirect:/login?error";
    }

}
