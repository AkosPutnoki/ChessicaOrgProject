package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class Pawn extends AbstractFigurine {

    public Pawn(int yCoord, int xCoord, Color color, Game game) {
        super(yCoord, xCoord, color, game);
    }


    @Override
    public TargetType validateMove(int yTargetCoord, int xTargetCoord) {
        if (this.color == Color.BLACK){
            if (yTargetCoord == this.yCoord && game.getGameState()[yTargetCoord][xTargetCoord] == null) {
                if(xTargetCoord - this.xCoord == 2 && this.xCoord == 1 && game.getGameState()[yTargetCoord][xTargetCoord -1] == null){
                    return TargetType.CLEAR;
                } else if(xTargetCoord - this.xCoord == 1){
                    return TargetType.CLEAR;
                }

            }else if (xTargetCoord - this.xCoord == 1 &&
                    Math.abs(yTargetCoord - this.yCoord) == 1 &&
                    (game.getGameState()[yTargetCoord][xTargetCoord] != null )){
                if(game.getGameState()[yTargetCoord][xTargetCoord].color == Color.WHITE) {
                    return TargetType.ENEMY;
                } else if(game.getGameState()[yTargetCoord][xTargetCoord].color == Color.BLACK){
                    return TargetType.FRIEND;
                }
            }
            return TargetType.INVALID;
        } else {
            if (yTargetCoord == this.yCoord && game.getGameState()[yTargetCoord][xTargetCoord] == null) {
                if(xTargetCoord - this.xCoord == -2 && this.xCoord == 6  && game.getGameState()[yTargetCoord][xTargetCoord +1] == null){
                    return TargetType.CLEAR;
                } else if(xTargetCoord - this.xCoord == -1){
                    return TargetType.CLEAR;
                }

            }else if(xTargetCoord - this.xCoord == -1 &&
                    Math.abs(yTargetCoord - this.yCoord) == 1 &&
                    (game.getGameState()[yTargetCoord][xTargetCoord] != null)){
                if(game.getGameState()[yTargetCoord][xTargetCoord].color == Color.BLACK) {
                    return TargetType.ENEMY;
                } else if(game.getGameState()[yTargetCoord][xTargetCoord].color == Color.WHITE){
                    return TargetType.FRIEND;
                }
            }
            return TargetType.INVALID;
        }
    }

    @Override
    public TargetType validatePath(int yTargetCoord, int xTargetCoord) {
        return validateMove(yTargetCoord, xTargetCoord);
    }
}
