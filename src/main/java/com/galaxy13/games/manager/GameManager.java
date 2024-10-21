package com.galaxy13.games.manager;

import com.galaxy13.games.cache.Cache;
import com.galaxy13.games.cache.GameCache;

public class GameManager extends AbstractManager{

    public GameManager() {
        super(new GameCache());
    }

    @Override
    public Cache getGameCache() {
        return this.cache;
    }
}
