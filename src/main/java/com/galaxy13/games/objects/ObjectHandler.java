package com.galaxy13.games.objects;

import com.galaxy13.games.field.GameField;

import java.util.UUID;

public interface ObjectHandler {
    void createNewMob(String name, GameField field);

    void changeObjectPosition(UUID id, double x, double y);
}
