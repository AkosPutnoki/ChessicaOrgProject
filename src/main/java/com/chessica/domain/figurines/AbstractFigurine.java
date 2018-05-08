package com.chessica.domain.figurines;

public abstract class AbstractFigurine {

    private int xCoord;
    private int yCoord;
    private Color color;

    public AbstractFigurine(int xCoord, int yCoord, Color color) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.color = color;
    }

    public abstract boolean validateMove(int xTargetCoord, int yTargetCoord);


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
