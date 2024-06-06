package com.galaxy13.games;

public abstract class Entity {
    private final String name;
    protected double coordinateX;
    protected double coordinateY;

    public Entity(String entityName){
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

    public String getName(){
        return name;
    }
}
