package com.galaxy13.games.objects;

import com.galaxy13.games.field.GameField;

import java.util.Objects;

public abstract class GameObject {
    private final Long id;
    private final String name;
    private final GameField gameField;

    public GameObject(Long id, String name, GameField gameField) {
        this.id = id;
        this.name = name;
        this.gameField = gameField;
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
