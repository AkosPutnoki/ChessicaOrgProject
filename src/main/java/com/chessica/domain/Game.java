package com.chessica.domain;

import com.chessica.domain.figurines.*;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {

    private static final int TABLESIZE = 8;
    private AbstractFigurine whiteKing;
    private AbstractFigurine blackKing;
    private AbstractFigurine[][] gameState;
    private List<AbstractFigurine> deadFigurines;

    public Game() {
        this.gameState = new AbstractFigurine[TABLESIZE][TABLESIZE];
        this.deadFigurines = new ArrayList<>();

        gameState[0][0] = new Rook(0,0, Color.BLACK, this);
        gameState[0][7] = new Rook(7,0, Color.BLACK, this);
        gameState[0][1] = new Knight(1,0, Color.BLACK, this);
        gameState[0][6] = new Knight(6,0, Color.BLACK, this);
        gameState[0][2] = new Bishop(2,0, Color.BLACK, this);
        gameState[0][5] = new Bishop(5,0, Color.BLACK, this);
        gameState[0][3] = new Queen(3,0, Color.BLACK, this);
        blackKing = new King(4,0, Color.BLACK, this);
        gameState[0][4] = blackKing;

        gameState[7][0] = new Rook(0,7, Color.WHITE, this);
        gameState[7][7] = new Rook(7,7, Color.WHITE, this);
        gameState[7][1] = new Knight(1,7, Color.WHITE, this);
        gameState[7][6] = new Knight(6,7, Color.WHITE, this);
        gameState[7][2] = new Bishop(2,7, Color.WHITE, this);
        gameState[7][5] = new Bishop(5,7, Color.WHITE, this);
        gameState[7][3] = new Queen(3,7, Color.WHITE, this);
        whiteKing = new King(4,7, Color.WHITE, this);
        gameState[7][4] = whiteKing;

        for(int i = 0; i< TABLESIZE; i++){
            gameState[1][i] = new Pawn(i, 1, Color.BLACK, this);
            gameState[6][i] = new Pawn(i, 6, Color.WHITE, this);
        }

    }

    public void moveFigurine(AbstractFigurine figurine, int yCoord, int xCoord) {
        TargetType result = figurine.validatePath(yCoord, xCoord);
        if (result == TargetType.ENEMY) {
            deadFigurines.add(gameState[yCoord][xCoord]);
            alterPosition(figurine, yCoord, xCoord);
        } else if (result == TargetType.CLEAR) {
            alterPosition(figurine, yCoord, xCoord);
        }
    }

    private void alterPosition(AbstractFigurine figurine, int xCoord, int yCoord){
        gameState[figurine.getyCoord()][figurine.getxCoord()] = null;
        figurine.setyCoord(xCoord);
        figurine.setxCoord(yCoord);
        gameState[xCoord][yCoord] = figurine;
    }

    public AbstractFigurine[][] getGameState() {
        return gameState;
    }

    public AbstractFigurine getWhiteKing() {
        return whiteKing;
    }

    public void setWhiteKing(AbstractFigurine whiteKing) {
        this.whiteKing = whiteKing;
    }

    public AbstractFigurine getBlackKing() {
        return blackKing;
    }

    public void setBlackKing(AbstractFigurine blackKing) {
        this.blackKing = blackKing;
    }
}
