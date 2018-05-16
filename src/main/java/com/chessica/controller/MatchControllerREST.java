package com.chessica.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MatchControllerREST {

    @PostMapping(value = "/api/game/pathvalidation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity pathValidation(@RequestBody Map<String, String> data){
        //TODO
        return null;
    }

}
