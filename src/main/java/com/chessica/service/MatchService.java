package com.chessica.service;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.AbstractFigurine;
import com.chessica.domain.figurines.enums.ResultType;
import com.chessica.domain.userRelated.Match;
import com.chessica.domain.userRelated.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class MatchService {

    public Map<String, List<List<String>>> validatePathFromCoordinates(User user, Map <String,Integer> data) throws IOException {
        Match currentMatch = user.getMatches().get(user.getMatches().size()-1);
        Game currentGame = currentMatch.getDeserializedGame();

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
        return resultMap;
    }

    public Match makeMove(User user, Map <String,Integer> data) throws IOException{
        Match currentMatch = user.getMatches().get(user.getMatches().size()-1);
        Game currentGame = currentMatch.getDeserializedGame();

        AbstractFigurine figurine = currentGame.getGameState()[data.get("Y")][data.get("X")];

        currentGame.moveFigurine(figurine, data.get("TargetY"), data.get("TargetX"));

        return currentMatch;
    }

    private void checkMatchState(Match match){

    }
}
