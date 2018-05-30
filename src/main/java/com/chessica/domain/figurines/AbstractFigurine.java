package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

import java.io.Serializable;

public abstract class AbstractFigurine implements Serializable {

    protected int yCoord;
    protected int xCoord;
    protected Color color;
    protected Game game;

    public AbstractFigurine(int yCoord, int xCoord, Color color, Game game) {
        this.yCoord = yCoord;
        this.xCoord = xCoord;
        this.color = color;
        this.game = game;
    }

    public abstract TargetType validateMove(int yTargetCoord, int xTargetCoord);

    public abstract TargetType validatePath(int yTargetCoord, int xTargetCoord);

    public Color getColor() {
        return color;
    }

    public int getyCoord() {
        return yCoord;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }
}
