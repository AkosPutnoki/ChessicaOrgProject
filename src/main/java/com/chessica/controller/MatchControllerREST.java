package com.chessica.controller;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.AbstractFigurine;
import com.chessica.domain.userRelated.Match;
import com.chessica.domain.userRelated.User;
import com.chessica.service.MatchService;
import com.chessica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
public class MatchControllerREST {

    @Autowired
    UserService userService;

    @Autowired
    MatchService matchService;

    @PostMapping(value = "/api/game/boardvalidation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity pathValidation(@RequestBody Map<String,Integer> data, HttpSession session){
        User user = userService.getUserById((long) session.getAttribute("id"));
        try {
            return ResponseEntity.ok(matchService.validatePathFromCoordinates(user, data));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(Collections.singletonMap("response", e.getMessage()));
        }
    }

    @PostMapping(value = "/api/game/makemove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity makeMove(@RequestBody Map<String,Integer> data, HttpSession session){
        User user = userService.getUserById((long) session.getAttribute("id"));
        //TODO
        return null;
    }



}
