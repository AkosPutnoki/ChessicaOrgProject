package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class Pawn extends AbstractFigurine {

    public Pawn(Game game,int xCoord, int yCoord, Color color) {
        super(xCoord, yCoord, color, game);

    }

    @Override
    public TargetType validateMove(int xTargetCoord, int yTargetCoord) {
        if (this.color == Color.BLACK){
            if (xTargetCoord == this.xCoord && game.getGameState()[xTargetCoord][yTargetCoord] == null) {
                if(yTargetCoord - this.yCoord == 2 && this.yCoord == 1 && game.getGameState()[xTargetCoord][yTargetCoord-1] == null){
                    return TargetType.CLEAR;
                } else if(yTargetCoord - this.yCoord == 1){
                    return TargetType.CLEAR;
                }

            }else if (yTargetCoord - this.yCoord == 1 &&
                    Math.abs(xTargetCoord - this.xCoord) == 1 &&
                    (game.getGameState()[xTargetCoord][yTargetCoord] != null &&
                        game.getGameState()[xTargetCoord][yTargetCoord].color == Color.WHITE)){
                        return TargetType.ENEMY;
            }
            return TargetType.INVALID;
        } else {
            if (xTargetCoord == this.xCoord && game.getGameState()[xTargetCoord][yTargetCoord] == null) {
                if(yTargetCoord - this.yCoord == -2 && this.yCoord == 6  && game.getGameState()[xTargetCoord][yTargetCoord+1] == null){
                    return TargetType.CLEAR;
                } else if(yTargetCoord - this.yCoord == -1){
                    return TargetType.CLEAR;
                }

            }else if(yTargetCoord - this.yCoord == -1 &&
                    Math.abs(xTargetCoord - this.xCoord) == 1 &&
                    (game.getGameState()[xTargetCoord][yTargetCoord] != null &&
                            game.getGameState()[xTargetCoord][yTargetCoord].color == Color.BLACK)){
                    return TargetType.ENEMY;
            }
            return TargetType.INVALID;
        }
    }

    @Override
    public TargetType validatePath(int xTargetCoord, int yTargetCoord) {
        return validateMove(xTargetCoord, yTargetCoord);
    }
}
