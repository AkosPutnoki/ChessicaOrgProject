package com.chessica.service;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.AbstractFigurine;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.ResultType;
import com.chessica.domain.figurines.enums.TargetType;
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

    private void checkMatchState(Match match) throws IOException {
        Game game = match.getDeserializedGame();
        AbstractFigurine blackKing = game.getBlackKing();
        AbstractFigurine whiteKing = game.getWhiteKing();

        List<Integer> blackList = new ArrayList<>();
        List<Integer> whiteList = new ArrayList<>();

        for(int y = -1; y<=1 ; y++){
            for (int x = -1; x <= 1; x++) {
                int currentBlackY = blackKing.getyCoord() + y;
                int currentBlackX = blackKing.getxCoord() + x;

                if(currentBlackX <= 7 && currentBlackX >= 0 &&
                    currentBlackY <= 7 && currentBlackY >= 0){
                    blackList.add(currentBlackY);
                    blackList.add(currentBlackX);
                }

                int currentWhiteY = whiteKing.getyCoord() + y;
                int currentWhiteX = whiteKing.getxCoord() + x;

                if(currentWhiteX <= 7 && currentWhiteX >= 0 &&
                        currentWhiteY <= 7 && currentWhiteY >= 0){
                    whiteList.add(currentWhiteY);
                    whiteList.add(currentWhiteX);
                }
            }
        }

        Map<List<Integer>, List<TargetType>> blackKingTargetMap = new HashMap<>();
        Map<List<Integer>, List<TargetType>> whiteKingTargetMap = new HashMap<>();

        for(AbstractFigurine[] list : game.getGameState()){
            for(AbstractFigurine figurine : list){
                if(figurine != null){
                    if(figurine.getColor() == Color.BLACK){
                        Iterator whiteKingIterator = whiteList.iterator();
                        while(whiteKingIterator.hasNext()){
                            int y = (int) whiteKingIterator.next();
                            int x = (int) whiteKingIterator.next();


                            whiteKingTargetList.add(figurine.validatePath(y, x);
                        }
                    } else{
                        Iterator blackKingIterator = blackList.iterator();
                        while(blackKingIterator.hasNext()){
                            blackKingTargetList.add(figurine.validatePath((int) blackKingIterator.next(), (int) blackKingIterator.next()));
                        }
                    }
                }
            }
        }


    }
}
