package com.chessica.domain.figurines;

import com.chessica.domain.Game;

public class Bishop extends AbstractFigurine {

    public Bishop(int xCoord, int yCoord, Color color, Game game) {
        super(xCoord, yCoord, color, game);
    }

    @Override
    public boolean validateMove(int xTargetCoord, int yTargetCoord) {
        return false;
    }

    @Override
    public boolean validatePath(int xTargetCoord, int yTargetCoord) {
        return false;
    }
}
