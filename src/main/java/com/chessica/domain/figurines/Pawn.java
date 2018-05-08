package com.chessica.domain.figurines;

import com.chessica.domain.Game;

public class Pawn extends AbstractFigurine {

    public Pawn(Game game,int xCoord, int yCoord, Color color) {
        super(xCoord, yCoord, color, game);

    }

    @Override
    public boolean validateMove(int xTargetCoord, int yTargetCoord) {
        if (this.color == Color.BLACK){
            if (xTargetCoord == this.xCoord && game.getGameState()[xTargetCoord][yTargetCoord] == null) {
                if(yTargetCoord - this.yCoord == 2 && this.yCoord == 1 && game.getGameState()[xTargetCoord][yTargetCoord-1] == null){
                    return true;
                } else if(yTargetCoord - this.yCoord == 1){
                    return true;
                }

            }else if (yTargetCoord - this.yCoord == 1 &&
                    Math.abs(xTargetCoord - this.xCoord) == 1 &&
                    (game.getGameState()[xTargetCoord][yTargetCoord] != null &&
                        game.getGameState()[xTargetCoord][yTargetCoord].color == Color.WHITE)){
                        return true;
            }
            return false;
        } else {
            if (xTargetCoord == this.xCoord && game.getGameState()[xTargetCoord][yTargetCoord] == null) {
                if(yTargetCoord - this.yCoord == -2 && this.yCoord == 6  && game.getGameState()[xTargetCoord][yTargetCoord+1] == null){
                    return true;
                } else if(yTargetCoord - this.yCoord == -1){
                    return true;
                }

            }else if(yTargetCoord - this.yCoord == -1 &&
                    Math.abs(xTargetCoord - this.xCoord) == 1 &&
                    (game.getGameState()[xTargetCoord][yTargetCoord] != null &&
                            game.getGameState()[xTargetCoord][yTargetCoord].color == Color.BLACK)){
                    return true;
            }
            return false;
        }
    }

    @Override
    public boolean validatePath(int xTargetCoord, int yTargetCoord) {
        return validateMove(xTargetCoord, yTargetCoord);
    }
}
