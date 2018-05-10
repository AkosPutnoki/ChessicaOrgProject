package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class Pawn extends AbstractFigurine {

    public Pawn(int xCoord, int yCoord, Color color, Game game) {
        super(xCoord, yCoord, color, game);

    }

    @Override
    public TargetType validateMove(int yTargetCoord, int xTargetCoord) {
        if (this.color == Color.BLACK){
            if (yTargetCoord == this.xCoord && game.getGameState()[yTargetCoord][xTargetCoord] == null) {
                if(xTargetCoord - this.yCoord == 2 && this.yCoord == 1 && game.getGameState()[yTargetCoord][xTargetCoord -1] == null){
                    return TargetType.CLEAR;
                } else if(xTargetCoord - this.yCoord == 1){
                    return TargetType.CLEAR;
                }

            }else if (xTargetCoord - this.yCoord == 1 &&
                    Math.abs(yTargetCoord - this.xCoord) == 1 &&
                    (game.getGameState()[yTargetCoord][xTargetCoord] != null &&
                        game.getGameState()[yTargetCoord][xTargetCoord].color == Color.WHITE)){
                        return TargetType.ENEMY;
            }
            return TargetType.INVALID;
        } else {
            if (yTargetCoord == this.xCoord && game.getGameState()[yTargetCoord][xTargetCoord] == null) {
                if(xTargetCoord - this.yCoord == -2 && this.yCoord == 6  && game.getGameState()[yTargetCoord][xTargetCoord +1] == null){
                    return TargetType.CLEAR;
                } else if(xTargetCoord - this.yCoord == -1){
                    return TargetType.CLEAR;
                }

            }else if(xTargetCoord - this.yCoord == -1 &&
                    Math.abs(yTargetCoord - this.xCoord) == 1 &&
                    (game.getGameState()[yTargetCoord][xTargetCoord] != null &&
                            game.getGameState()[yTargetCoord][xTargetCoord].color == Color.BLACK)){
                    return TargetType.ENEMY;
            }
            return TargetType.INVALID;
        }
    }

    @Override
    public TargetType validatePath(int yTargetCoord, int xTargetCoord) {
        return validateMove(yTargetCoord, xTargetCoord);
    }
}
