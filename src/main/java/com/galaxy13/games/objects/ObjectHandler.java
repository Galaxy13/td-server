package com.galaxy13.games.objects;

import com.galaxy13.games.field.GameField;

import java.util.UUID;

public interface ObjectHandler {
    void createGameObject(Class<? extends GameObject> objClass, String name, GameField field);

    void changeObjectState(UUID id, String state);
}
