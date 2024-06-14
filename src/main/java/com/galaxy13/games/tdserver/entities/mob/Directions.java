package com.galaxy13.games.tdserver.entities.mob;

public enum Directions {
    UP(0.0, 0.5),
    DOWN(0.0, -0.5),
    LEFT(-0.5, 0.0),
    RIGHT(0.5, 0.0);

    private final double x;
    private final double y;


    Directions(double speedX, double speedY) {
        x = speedX;
        y = speedY;
    }

    public double getSpeedX() {
        return x;
    }

    public double getSpeedY() {
        return y;
    }
}
