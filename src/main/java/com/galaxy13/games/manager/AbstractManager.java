package com.galaxy13.games.manager;

import com.galaxy13.games.cache.Cache;

public abstract class AbstractManager implements Manager{
    protected final Cache cache;

    public AbstractManager(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void processEvent(Event event) {
    }
}
