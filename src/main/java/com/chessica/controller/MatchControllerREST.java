package com.chessica.controller;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.AbstractFigurine;
import com.chessica.domain.userRelated.Match;
import com.chessica.domain.userRelated.User;
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

    @PostMapping(value = "/api/game/pathvalidation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity pathValidation(@RequestBody Map<String, Integer> data, HttpSession session){
        User user = userService.getUserById((long) session.getAttribute("id"));
        Match currentMatch = user.getMatches().get(user.getMatches().size()-1);
        Game currentGame = null;
        try {
            currentGame = currentMatch.getDeserializedGame();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(Collections.singletonMap("response", e.getMessage()));
        }

        AbstractFigurine figurine = currentGame.getGameState()[data.get("Y")][data.get("X")];
        Map<String, List<List<String>>> resultMap = new HashMap<>();

        List<List<String>> resultMatrix = new ArrayList<>();
        for(int y = 0; y < currentGame.getGameState().length; y++){
            List<String> resultList = new ArrayList<>();
            for(int x = 0; x < currentGame.getGameState()[y].length; x++) {
                resultList.add(figurine.validatePath(y, x).toString());
            }
            resultMatrix.add(resultList);
        }

        resultMap.put("TargetTypeMatrix", resultMatrix);

        return ResponseEntity.ok(resultMap);
    }

}
