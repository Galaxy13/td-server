package com.galaxy13.games.objects;

import com.galaxy13.games.field.GameField;

import java.util.UUID;

public class Mob extends GameObject {
    private Mob(UUID id, String name, GameField field) {
        super(id, name, field);
    }

    public static Mob createWithFixedID(UUID id, String name, GameField field) {
        return new Mob(id, name, field);
    }

    public static Mob create(String name, GameField field) {
        return new Mob(UUID.randomUUID(), name, field);
    }
}
