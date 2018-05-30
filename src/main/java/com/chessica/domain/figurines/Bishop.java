package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class Bishop extends AbstractFigurine {

    public Bishop(int yCoord, int xCoord, Color color, Game game) {
        super(yCoord, xCoord, color, game);
    }

    @Override
    public TargetType validateMove(int yTargetCoord, int xTargetCoord) {
        if(Math.abs(this.yCoord - yTargetCoord) == Math.abs(this.xCoord - xTargetCoord)){
            if(game.getGameState()[yTargetCoord][xTargetCoord] != null &&
                    game.getGameState()[yTargetCoord][xTargetCoord].getColor() != this.color) {
                return TargetType.ENEMY;
            } else if(game.getGameState()[yTargetCoord][xTargetCoord] == null){
                return TargetType.CLEAR;
            } else if(game.getGameState()[yTargetCoord][xTargetCoord].getColor() == this.color){
                return TargetType.FRIEND;
            }
        }
        return TargetType.INVALID;
    }

    @Override
    public TargetType validatePath(int yTargetCoord, int xTargetCoord) {
        if(validateMove(yTargetCoord, xTargetCoord) == TargetType.INVALID){
            return TargetType.INVALID;
        }

        int xDiff = yTargetCoord -this.yCoord;
        int yDiff = xTargetCoord -this.xCoord;

        if(xDiff > 0){
            if(yDiff > 0){
                for(int i = 1; i < xDiff; i++){
                    if(validateMove(this.yCoord +i, this.xCoord +i) != TargetType.CLEAR){
                        return TargetType.INVALID;
                    }
                }
            } else if(yDiff < 0){
                for(int i = 1; i < xDiff; i++){
                    if(validateMove(this.yCoord +i, this.xCoord -i) != TargetType.CLEAR){
                        return TargetType.INVALID;
                    }
                }
            }

        } else if(xDiff < 0) {
            if (yDiff > 0) {
                for (int i = 1; i < xDiff; i++) {
                    if (validateMove(this.yCoord - i, this.xCoord + i) != TargetType.CLEAR) {
                        return TargetType.INVALID;
                    }
                }
            } else if (yDiff < 0) {
                for (int i = 1; i < xDiff; i++) {
                    if (validateMove(this.yCoord - i, this.xCoord - i) != TargetType.CLEAR) {
                        return TargetType.INVALID;
                    }
                }
            }

        }
        return validateMove(yTargetCoord, xTargetCoord);
    }
}
