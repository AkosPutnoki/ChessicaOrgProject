package com.chessica.domain;

import com.chessica.domain.figurines.AbstractFigurine;
import com.chessica.domain.figurines.TargetType;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int TABLESIZE = 8;
    private AbstractFigurine[][] gameState;
    private List<AbstractFigurine> deadFigurines;

    public Game() {
        this.gameState = new AbstractFigurine[TABLESIZE][TABLESIZE];
        this.deadFigurines = new ArrayList<>();
    }

    public void moveFigurine(AbstractFigurine figurine, int xCoord, int yCoord){
        if (figurine.validatePath(xCoord, yCoord) != TargetType.INVALID){
            AbstractFigurine target = gameState[xCoord][yCoord];
            if (target != null && figurine.getColor() != target.getColor()){
                deadFigurines.add(target);
                alterPosition(figurine, xCoord, yCoord);
            } else if (target == null){
                alterPosition(figurine, xCoord, yCoord);
            }
        }
    }

    private void alterPosition(AbstractFigurine figurine, int xCoord, int yCoord){
        gameState[figurine.getxCoord()][figurine.getyCoord()] = null;
        figurine.setxCoord(xCoord);
        figurine.setyCoord(yCoord);
        gameState[xCoord][yCoord] = figurine;
    }

    public AbstractFigurine[][] getGameState() {
        return gameState;
    }
}
