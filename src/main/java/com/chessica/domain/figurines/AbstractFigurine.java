package com.chessica.domain.figurines;

import com.chessica.domain.Game;

public abstract class AbstractFigurine {

    protected int xCoord;
    protected int yCoord;
    protected Color color;
    protected Game game;

    public AbstractFigurine(int xCoord, int yCoord, Color color, Game game) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.color = color;
        this.game = game;
    }

    public abstract TargetType validateMove(int xTargetCoord, int yTargetCoord);

    public abstract TargetType validatePath(int xTargetCoord, int yTargetCoord);

    public Color getColor() {
        return color;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
