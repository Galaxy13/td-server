package com.galaxy13.games.cache;

import com.galaxy13.games.field.GameField;
import com.galaxy13.games.objects.GameObject;

public interface State {
    void putObjectState(GameObject gameObject);

    void putFieldState(GameField gameField);
}
