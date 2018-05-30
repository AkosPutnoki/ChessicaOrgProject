package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

public class King extends AbstractFigurine {

    public King(int yCoord, int xCoord, Color color, Game game) {
        super(yCoord, xCoord, color, game);
    }


    @Override
    public TargetType validateMove(int yTargetCoord, int xTargetCoord) {
        if (Math.abs(this.yCoord - yTargetCoord) <= 1 && Math.abs(this.xCoord - xTargetCoord) <= 1) {
            if (game.getGameState()[yTargetCoord][xTargetCoord] != null &&
                    game.getGameState()[yTargetCoord][xTargetCoord].getColor() != this.color) {
                return TargetType.ENEMY;
            } else if (game.getGameState()[yTargetCoord][xTargetCoord] == null) {
                return TargetType.CLEAR;
            } else if (game.getGameState()[yTargetCoord][xTargetCoord].getColor() == this.color) {
                return TargetType.FRIEND;
            }
        }
        return TargetType.INVALID;
    }

    @Override
    public TargetType validatePath(int yTargetCoord, int xTargetCoord) {
        return validateMove(yTargetCoord, xTargetCoord);
    }
}
