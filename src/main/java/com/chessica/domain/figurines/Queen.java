package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class Queen extends AbstractFigurine {

    public Queen(int xCoord, int yCoord, Color color, Game game) {
        super(xCoord, yCoord, color, game);
    }

    @Override
    public TargetType validateMove(int xTargetCoord, int yTargetCoord) {
        if(Math.abs(this.xCoord-xTargetCoord) == Math.abs(this.yCoord-yTargetCoord)){
            if(game.getGameState()[xTargetCoord][yTargetCoord] != null &&
                    game.getGameState()[xTargetCoord][yTargetCoord].getColor() != this.color) {
                return TargetType.ENEMY;
            } else if(game.getGameState()[xTargetCoord][yTargetCoord] == null){
                return TargetType.CLEAR;
            }
        } else if ((yTargetCoord != this.getyCoord() && xTargetCoord == this.getxCoord())
                || (xTargetCoord != this.getxCoord() && yTargetCoord == this.getyCoord())) {
            if ((game.getGameState()[xTargetCoord][yTargetCoord] != null &&
                    game.getGameState()[xTargetCoord][yTargetCoord].color != this.color)) {
                return TargetType.ENEMY;
            } else if(game.getGameState()[xTargetCoord][yTargetCoord] == null){
                return TargetType.CLEAR;
            }
        }
        return TargetType.INVALID;
    }

    @Override
    public TargetType validatePath(int xTargetCoord, int yTargetCoord) {
        if (validateMove(xTargetCoord, yTargetCoord) != TargetType.INVALID){
            if ((yTargetCoord != this.getyCoord() && xTargetCoord == this.getxCoord())
                    || (xTargetCoord != this.getxCoord() && yTargetCoord == this.getyCoord())){
                if (xTargetCoord == this.xCoord){
                    for (int i=1; i < Math.abs(yTargetCoord - this.yCoord); i++){
                        if (yTargetCoord > this.yCoord){
                            if (this.validateMove(xTargetCoord, this.yCoord + i) != TargetType.CLEAR){
                                return TargetType.INVALID;
                            }
                        } else if(yTargetCoord < this.yCoord){
                            if (this.validateMove(xTargetCoord, this.yCoord - i) != TargetType.CLEAR){
                                return TargetType.INVALID;
                            }
                        }
                    }
                } else if (yTargetCoord == this.yCoord){
                    for (int i=1; i < Math.abs(xTargetCoord - this.xCoord); i++){
                        if (xTargetCoord > this.xCoord){
                            if (this.validateMove(this.xCoord + i, yTargetCoord) != TargetType.CLEAR){
                                return TargetType.INVALID;
                            }
                        } else if (xTargetCoord < this.xCoord){
                            if (this.validateMove(this.xCoord - i, yTargetCoord) != TargetType.CLEAR){
                                return TargetType.INVALID;
                            }
                        }
                    }
                }
                return validateMove(xTargetCoord, yTargetCoord);

            } else if (Math.abs(this.xCoord-xTargetCoord) == Math.abs(this.yCoord-yTargetCoord)){
                int xDiff = xTargetCoord-this.xCoord;
                int yDiff = yTargetCoord-this.yCoord;

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
                return validateMove(xTargetCoord, yTargetCoord);
            }
        }
        return TargetType.INVALID;
    }
}
