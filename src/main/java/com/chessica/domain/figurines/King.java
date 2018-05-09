package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class King extends AbstractFigurine {

    public King(int xCoord, int yCoord, Color color, Game game) {
        super(xCoord, yCoord, color, game);
    }

    @Override
    public TargetType validateMove(int xTargetCoord, int yTargetCoord) {
        if(Math.abs(this.xCoord-xTargetCoord) <=1 && Math.abs(this.yCoord-yTargetCoord) <= 1){
            if(game.getGameState()[xTargetCoord][yTargetCoord] != null &&
                    game.getGameState()[xTargetCoord][yTargetCoord].getColor() != this.color){
                return TargetType.ENEMY;
            }
            if(game.getGameState()[xTargetCoord][yTargetCoord] == null){
                return TargetType.CLEAR;
            }
        }
        return TargetType.INVALID;
    }

    @Override
    public TargetType validatePath(int xTargetCoord, int yTargetCoord) {
        return validateMove(xTargetCoord, yTargetCoord);
    }
}
