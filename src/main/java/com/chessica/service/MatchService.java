package com.chessica.service;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.AbstractFigurine;
import com.chessica.domain.figurines.King;
import com.chessica.domain.figurines.enums.CheckState;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;
import com.chessica.domain.userRelated.Match;
import com.chessica.domain.userRelated.User;
import org.hibernate.annotations.Check;
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

        List<String> blackList = new ArrayList<>();
        List<String> whiteList = new ArrayList<>();

        for(int y = -1; y<=1 ; y++){
            for (int x = -1; x <= 1; x++) {
                int currentBlackY = blackKing.getxCoord() + y;
                int currentBlackX = blackKing.getyCoord() + x;

                if(currentBlackX <= 7 && currentBlackX >= 0 &&
                    currentBlackY <= 7 && currentBlackY >= 0){

                    blackList.add("" + currentBlackY + currentBlackX);

                }

                int currentWhiteY = whiteKing.getxCoord() + y;
                int currentWhiteX = whiteKing.getyCoord() + x;

                if(currentWhiteX <= 7 && currentWhiteX >= 0 &&
                        currentWhiteY <= 7 && currentWhiteY >= 0){

                    whiteList.add("" + currentWhiteY + currentBlackX);
                }
            }
        }

        Map<String, List<TargetType>> blackKingTargetMap = new HashMap<>();
        Map<String, List<TargetType>> whiteKingTargetMap = new HashMap<>();

        Iterator whiteKingIterator = whiteList.iterator();
        Iterator blackKingIterator = blackList.iterator();


        while(whiteKingIterator.hasNext()){
            String yx = (String) whiteKingIterator.next();
            int y = (int) yx.charAt(0);
            int x = (int) yx.charAt(1);

            whiteKingTargetMap.put(yx, kingCoordinateCheckAnalyzer(game, Color.WHITE, y, x));
        }

        while(blackKingIterator.hasNext()){
            String yx = (String) blackKingIterator.next();
            int y = (int) yx.charAt(0);
            int x = (int) yx.charAt(1);

            blackKingTargetMap.put(yx, kingCoordinateCheckAnalyzer(game, Color.BLACK, y, x));
        }


        match.setCheckState(CheckState.NOCHECK);

        for(Map.Entry<String, List<TargetType>> pair : whiteKingTargetMap.entrySet()){
            //Check for king's current coordinates
            if(pair.getKey().equals("" + whiteKing.getyCoord() + whiteKing.getxCoord())){
                if(pair.getValue().contains(TargetType.ENEMY)){
                    match.setCheckState(CheckState.WHITECHECK);
                }
            }
        }
        for(Map.Entry<String, List<TargetType>> pair : blackKingTargetMap.entrySet()){
            //Check for king's current coordinates
            if(pair.getKey().equals("" + blackKing.getyCoord() + blackKing.getxCoord())){
                if(pair.getValue().contains(TargetType.ENEMY)){
                    if(match.getCheckState() == CheckState.WHITECHECK) {
                        match.setCheckState(CheckState.BOTHCHECK);
                    } else {
                        match.setCheckState(CheckState.BLACKCHECK);
                    }
                }
            }
        }

    }

    public List<TargetType> kingCoordinateCheckAnalyzer(Game game, Color kingColor, int y, int x){

        List<TargetType> currentYXTargetList = new ArrayList<>();
        for(AbstractFigurine[] list : game.getGameState()){
            for(AbstractFigurine figurine : list){
                if(figurine != null){
                    if(figurine.getColor() != kingColor){
                        currentYXTargetList.add(figurine.validatePath(y, x));
                    }
                }
            }
        }

        return currentYXTargetList;
    }
}
