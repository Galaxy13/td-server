package com.galaxy13.games.tdserver.entities.mob;

import com.galaxy13.games.tdserver.entities.interfaces.Movable;
import com.galaxy13.games.tdserver.entities.Entity;

public class Mob extends Entity implements Movable {
    public Mob(String entityName) {
        super(entityName);
    }

    @Override
    public void move(double speedX, double speedY) {
        coordinateX += speedX;
        coordinateY += speedY;
    }
}
