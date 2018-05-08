package com.chessica.domain.figurines;

import com.chessica.domain.Game;

import java.security.cert.X509Certificate;

public class Bishop extends AbstractFigurine {

    public Bishop(int xCoord, int yCoord, Color color, Game game) {
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
        }
        return TargetType.INVALID;
    }

    @Override
    public TargetType validatePath(int xTargetCoord, int yTargetCoord) {
        if(validateMove(xTargetCoord, yTargetCoord) == TargetType.INVALID){
            return TargetType.INVALID;
        }

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
