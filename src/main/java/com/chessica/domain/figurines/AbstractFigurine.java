package com.chessica.domain.figurines;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;

import java.io.Serializable;

public abstract class AbstractFigurine implements Serializable {

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

    public abstract TargetType validateMove(int yTargetCoord, int xTargetCoord);

    public abstract TargetType validatePath(int yTargetCoord, int xTargetCoord);

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
