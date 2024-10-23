package com.galaxy13.games.listener;

import com.galaxy13.games.manager.CacheManager;

public abstract class AbstractListener implements Listener{
    private final String listenerName;
    private final String host;
    private final int port;
    private final CacheManager cache;

    public AbstractListener(CacheManager cacheManager, String listenerName, String host, int port) {
        this.listenerName = listenerName;
        this.host = host;
        this.port = port;
        this.cache = cacheManager;
    }
}
