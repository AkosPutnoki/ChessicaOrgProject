package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class Knight extends AbstractFigurine {

    public Knight(int xCoord, int yCoord, Color color, Game game) {
        super(xCoord, yCoord, color, game);
    }

    @Override
    public TargetType validateMove(int yTargetCoord, int xTargetCoord) {
        if ((Math.abs(yTargetCoord - this.xCoord) == 1 && Math.abs(xTargetCoord - this.yCoord) == 2)
            || (Math.abs(yTargetCoord - this.xCoord) == 2 && Math.abs(xTargetCoord - this.yCoord) == 1)){
            if (game.getGameState()[yTargetCoord][xTargetCoord] != null &&
                    game.getGameState()[yTargetCoord][xTargetCoord].color != this.color){
                return TargetType.ENEMY;
            } else if (game.getGameState()[yTargetCoord][xTargetCoord] == null){
                return TargetType.CLEAR;
            }
        }
        return TargetType.INVALID;
    }

    @Override
    public TargetType validatePath(int yTargetCoord, int xTargetCoord) {
        return validateMove(yTargetCoord, xTargetCoord);
    }
}
