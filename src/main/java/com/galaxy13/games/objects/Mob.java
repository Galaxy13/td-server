package com.galaxy13.games.objects;

import com.galaxy13.games.field.GameField;

import java.util.UUID;

public class Mob extends GameObject implements Position {
    private final MobPosition position;

    private Mob(UUID id, String name, GameField field) {
        super(id, name, field);
        this.position = new MobPosition();
    }

    public static Mob createWithFixedID(UUID id, String name, GameField field) {
        return new Mob(id, name, field);
    }

    public static Mob create(String name, GameField field) {
        return new Mob(UUID.randomUUID(), name, field);
    }

    @Override
    public void setX(double x) {
        this.position.setX(x);
    }

    @Override
    public void setY(double y) {
        this.position.setY(y);
    }

    @Override
    public double getX() {
        return this.position.getX();
    }

    @Override
    public double getY() {
        return this.position.getY();
    }

    private static class MobPosition{
        double x, y;
        public MobPosition() {
            this.x = 0;
            this.y = 0;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }
}
