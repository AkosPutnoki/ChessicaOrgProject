package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class Knight extends AbstractFigurine {

    public Knight(int xCoord, int yCoord, Color color, Game game) {
        super(xCoord, yCoord, color, game);
    }

    @Override
    public TargetType validateMove(int xTargetCoord, int yTargetCoord) {
        if ((Math.abs(xTargetCoord - this.xCoord) == 1 && Math.abs(yTargetCoord - this.yCoord) == 2)
            || (Math.abs(xTargetCoord - this.xCoord) == 2 && Math.abs(yTargetCoord - this.yCoord) == 1)){
            if (game.getGameState()[xTargetCoord][yTargetCoord] != null &&
                    game.getGameState()[xTargetCoord][yTargetCoord].color != this.color){
                return TargetType.ENEMY;
            } else if (game.getGameState()[xTargetCoord][yTargetCoord] == null){
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
