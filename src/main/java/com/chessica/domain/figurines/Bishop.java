package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class Bishop extends AbstractFigurine {

    public Bishop(int xCoord, int yCoord, Color color, Game game) {
        super(xCoord, yCoord, color, game);
    }

    @Override
    public TargetType validateMove(int yTargetCoord, int xTargetCoord) {
        if(Math.abs(this.xCoord- yTargetCoord) == Math.abs(this.yCoord- xTargetCoord)){
            if(game.getGameState()[yTargetCoord][xTargetCoord] != null &&
                    game.getGameState()[yTargetCoord][xTargetCoord].getColor() != this.color) {
                return TargetType.ENEMY;
            } else if(game.getGameState()[yTargetCoord][xTargetCoord] == null){
                return TargetType.CLEAR;
            }
        }
        return TargetType.INVALID;
    }

    @Override
    public TargetType validatePath(int yTargetCoord, int xTargetCoord) {
        if(validateMove(yTargetCoord, xTargetCoord) == TargetType.INVALID){
            return TargetType.INVALID;
        }

        int xDiff = yTargetCoord -this.xCoord;
        int yDiff = xTargetCoord -this.yCoord;

        if(xDiff > 0){
            if(yDiff > 0){
                for(int i = 1; i < xDiff; i++){
                    if(validateMove(this.xCoord+i, this.yCoord+i) != TargetType.CLEAR){
                        return TargetType.INVALID;
                    }
                }
            } else if(yDiff < 0){
                for(int i = 1; i < xDiff; i++){
                    if(validateMove(this.xCoord+i, this.yCoord-i) != TargetType.CLEAR){
                        return TargetType.INVALID;
                    }
                }
            }

        } else if(xDiff < 0) {
            if (yDiff > 0) {
                for (int i = 1; i < xDiff; i++) {
                    if (validateMove(this.xCoord - i, this.yCoord + i) != TargetType.CLEAR) {
                        return TargetType.INVALID;
                    }
                }
            } else if (yDiff < 0) {
                for (int i = 1; i < xDiff; i++) {
                    if (validateMove(this.xCoord - i, this.yCoord - i) != TargetType.CLEAR) {
                        return TargetType.INVALID;
                    }
                }
            }

        }
        return validateMove(yTargetCoord, xTargetCoord);
    }
}
