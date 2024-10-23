package com.galaxy13.games.objects;

import com.galaxy13.games.field.GameField;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

public class ObjectHandlerImpl implements ObjectHandler {
    private final Map<UUID, GameObject> objects;

    public ObjectHandlerImpl() {
        objects = new HashMap<>();
    }

    @Override
    public void createGameObject(Class<? extends GameObject> objClass, String name, GameField field) {
    }

    @Override
    public void changeObjectState(UUID id, String state) {
    }
}
