package com.galaxy13.games;

public class Mob extends Entity implements Movable{
    public Mob(String entityName) {
        super(entityName);
    }

    @Override
    public void move(double speedX, double speedY) {
        coordinateX += speedX;
        coordinateY += speedY;
    }
}
