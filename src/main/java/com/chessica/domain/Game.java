package com.chessica.domain;

import com.chessica.domain.figurines.*;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int TABLESIZE = 8;
    private AbstractFigurine[][] gameState;
    private List<AbstractFigurine> deadFigurines;

    public Game() {
        this.gameState = new AbstractFigurine[TABLESIZE][TABLESIZE];
        this.deadFigurines = new ArrayList<>();

        gameState[0][0] = new Rook(0,0, Color.BLACK, this);
        gameState[7][0] = new Rook(7,0, Color.BLACK, this);
        gameState[1][0] = new Knight(1,0, Color.BLACK, this);
        gameState[6][0] = new Knight(6,0, Color.BLACK, this);
        gameState[2][0] = new Bishop(2,0, Color.BLACK, this);
        gameState[5][0] = new Bishop(5,0, Color.BLACK, this);
        gameState[3][0] = new Queen(3,0, Color.BLACK, this);
        gameState[4][0] = new King(4,0, Color.BLACK, this);

        gameState[0][7] = new Rook(0,7, Color.WHITE, this);
        gameState[7][7] = new Rook(7,7, Color.WHITE, this);
        gameState[1][7] = new Knight(1,7, Color.WHITE, this);
        gameState[6][7] = new Knight(6,7, Color.WHITE, this);
        gameState[2][7] = new Bishop(2,7, Color.WHITE, this);
        gameState[5][7] = new Bishop(5,7, Color.WHITE, this);
        gameState[3][7] = new Queen(3,7, Color.WHITE, this);
        gameState[4][7] = new King(4,7, Color.WHITE, this);

        for(int i = 0; i< TABLESIZE; i++){
            gameState[i][1] = new Pawn(i, 1, Color.BLACK, this);
            gameState[i][6] = new Pawn(i, 6, Color.WHITE, this);
        }

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
