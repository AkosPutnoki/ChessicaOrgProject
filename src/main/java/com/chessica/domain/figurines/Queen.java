package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class Queen extends AbstractFigurine {

    public Queen(int xCoord, int yCoord, Color color, Game game) {
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
        } else if ((xTargetCoord != this.getyCoord() && yTargetCoord == this.getxCoord())
                || (yTargetCoord != this.getxCoord() && xTargetCoord == this.getyCoord())) {
            if ((game.getGameState()[yTargetCoord][xTargetCoord] != null &&
                    game.getGameState()[yTargetCoord][xTargetCoord].color != this.color)) {
                return TargetType.ENEMY;
            } else if(game.getGameState()[yTargetCoord][xTargetCoord] == null){
                return TargetType.CLEAR;
            }
        }
        return TargetType.INVALID;
    }

    @Override
    public TargetType validatePath(int yTargetCoord, int xTargetCoord) {
        if (validateMove(yTargetCoord, xTargetCoord) != TargetType.INVALID){
            if ((xTargetCoord != this.getyCoord() && yTargetCoord == this.getxCoord())
                    || (yTargetCoord != this.getxCoord() && xTargetCoord == this.getyCoord())){
                if (yTargetCoord == this.xCoord){
                    for (int i = 1; i < Math.abs(xTargetCoord - this.yCoord); i++){
                        if (xTargetCoord > this.yCoord){
                            if (this.validateMove(yTargetCoord, this.yCoord + i) != TargetType.CLEAR){
                                return TargetType.INVALID;
                            }
                        } else if(xTargetCoord < this.yCoord){
                            if (this.validateMove(yTargetCoord, this.yCoord - i) != TargetType.CLEAR){
                                return TargetType.INVALID;
                            }
                        }
                    }
                } else if (xTargetCoord == this.yCoord){
                    for (int i = 1; i < Math.abs(yTargetCoord - this.xCoord); i++){
                        if (yTargetCoord > this.xCoord){
                            if (this.validateMove(this.xCoord + i, xTargetCoord) != TargetType.CLEAR){
                                return TargetType.INVALID;
                            }
                        } else if (yTargetCoord < this.xCoord){
                            if (this.validateMove(this.xCoord - i, xTargetCoord) != TargetType.CLEAR){
                                return TargetType.INVALID;
                            }
                        }
                    }
                }
                return validateMove(yTargetCoord, xTargetCoord);

            } else if (Math.abs(this.xCoord- yTargetCoord) == Math.abs(this.yCoord- xTargetCoord)){
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
        return TargetType.INVALID;
    }
}
