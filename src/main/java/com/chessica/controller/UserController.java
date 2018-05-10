package com.chessica.controller;

import com.chessica.domain.userRelated.User;
import com.chessica.exception.FailedDataVerificationException;
import com.chessica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user/registration")
    public String registration(@RequestParam(value = "username") String username,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "password") String password,
                               HttpSession session) {

        try{
            User currentUser = userService.registration(username, password, email);
            session.setAttribute("id", Long.valueOf(currentUser.getId()).intValue());
            session.setAttribute("username", username);
            return "dashboard";
        }catch (FailedDataVerificationException e){
            e.printStackTrace();
            return "redirect:/?incorrect=registration";
        }
    }


    @PostMapping(value = "/user/login")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        HttpSession session) {
        try{
            User currentUser = userService.login(username, password);
            session.setAttribute("id", Long.valueOf(currentUser.getId()).intValue());
            session.setAttribute("username", username);
            return "dashboard";
        }catch (FailedDataVerificationException e){
            e.printStackTrace();
            return "redirect:/?incorrect=login";
        }
    }

}
