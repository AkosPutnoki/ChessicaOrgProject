package com.chessica.domain.figurines;

import com.chessica.domain.Game;

public class Pawn extends AbstractFigurine {

    private Game game;

    public Pawn(Game game,int xCoord, int yCoord, Color color) {
        super(xCoord, yCoord, color);
        this.game = game;
    }

    @Override
    public boolean validateMove(int xTargetCoord, int yTargetCoord) {
        if (this.getColor() == Color.BLACK){
            if (yTargetCoord - this.getyCoord() > 0 &&
                    xTargetCoord == this.getxCoord()) {
                if(yTargetCoord - this.getyCoord() == 2 && this.getyCoord() == 1){
                    return true;
                } else if(yTargetCoord - this.getyCoord() == 1){
                    return true;
                }

            }else if (yTargetCoord - this.getyCoord() == 1 &&
                    Math.abs(xTargetCoord - this.getxCoord()) == 1 &&
                    (game.getGameState()[xTargetCoord][yTargetCoord] != null &&
                        game.getGameState()[xTargetCoord][yTargetCoord].getColor() == Color.WHITE)){
                        return true;
            }
        }
        return false;
    }
}
