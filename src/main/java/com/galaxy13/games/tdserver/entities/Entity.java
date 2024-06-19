package com.galaxy13.games.tdserver.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Entity implements Serializable {
    private final String name;
    protected double coordinateX;
    protected double coordinateY;

    public Entity(String entityName) {
        this.name = entityName;
        this.coordinateX = 0.0;
        this.coordinateY = 0.0;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> serialize() {
        Map<String, Object> entityInfo = new HashMap<>();
        entityInfo.put("name", name);
        entityInfo.put("x", coordinateX);
        entityInfo.put("y", coordinateY);
        return entityInfo;
    }
}
