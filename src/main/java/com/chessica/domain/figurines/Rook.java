package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class Rook extends AbstractFigurine {

    public Rook(int xCoord, int yCoord, Color color, Game game) {
        super(xCoord, yCoord, color, game);
    }

    @Override
    public TargetType validateMove(int xTargetCoord, int yTargetCoord) {
        if ((yTargetCoord != this.getyCoord() && xTargetCoord == this.getxCoord())
                || (xTargetCoord != this.getxCoord() && yTargetCoord == this.getyCoord())) {
            if ((game.getGameState()[xTargetCoord][yTargetCoord] != null &&
                    game.getGameState()[xTargetCoord][yTargetCoord].getColor() != this.color)) {
                return TargetType.ENEMY;
            } else if(game.getGameState()[xTargetCoord][yTargetCoord] == null){
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
    }
}
