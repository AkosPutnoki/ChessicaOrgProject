package com.chessica.domain.figurines;

import com.chessica.domain.Game;

public class Rook extends AbstractFigurine {

    public Rook(int xCoord, int yCoord, Color color, Game game) {
        super(xCoord, yCoord, color, game);
    }

    @Override
    public boolean validateMove(int xTargetCoord, int yTargetCoord) {
        if ((yTargetCoord != this.getyCoord() && xTargetCoord == this.getxCoord())
                || (xTargetCoord != this.getxCoord() && yTargetCoord == this.getyCoord())) {
            if ((game.getGameState()[xTargetCoord][yTargetCoord] != null &&
                    game.getGameState()[xTargetCoord][yTargetCoord].color != this.color) ||
                    game.getGameState()[xTargetCoord][yTargetCoord] == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validatePath(int xTargetCoord, int yTargetCoord) {
        if (!validateMove(xTargetCoord, yTargetCoord)){
            return false;
        }
        if (xTargetCoord == this.xCoord){
            for (int i=1; i <= Math.abs(yTargetCoord - this.yCoord); i++){
                if (yTargetCoord > this.yCoord){
                    if (!this.validateMove(xTargetCoord, this.yCoord + i)){
                        return false;
                    }
                } else if(yTargetCoord < this.yCoord){
                    if (!this.validateMove(xTargetCoord, this.yCoord - i)){
                        return false;
                    }
                }
            }
        } else if (yTargetCoord == this.yCoord){
            for (int i=1; i <= Math.abs(xTargetCoord - this.xCoord); i++){
                if (xTargetCoord > this.xCoord){
                    if (!this.validateMove(this.xCoord + i, yTargetCoord)){
                        return false;
                    }
                } else if (xTargetCoord < this.xCoord){
                    if (!this.validateMove(this.xCoord - i, yTargetCoord)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
