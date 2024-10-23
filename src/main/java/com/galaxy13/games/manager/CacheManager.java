package com.galaxy13.games.manager;

import com.galaxy13.games.cache.State;

public interface CacheManager {
    State getState();

    void sendClientCommand(String command);
}
