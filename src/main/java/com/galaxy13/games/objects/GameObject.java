package com.galaxy13.games.objects;

import com.galaxy13.games.field.GameField;

import java.util.Objects;
import java.util.UUID;

public abstract class GameObject {
    private final UUID id;
    private final String name;
    private final GameField gameField;

    protected GameObject(UUID id, String name, GameField gameField) {
        this.id = id;
        this.name = name;
        this.gameField = gameField;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
