package com.galaxy13.games.manager;

import com.galaxy13.games.cache.Cache;

public interface Manager {
    void processEvent(Event event);

    Cache getGameCache();
}
