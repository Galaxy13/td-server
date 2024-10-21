package com.galaxy13.games.listener;

import com.galaxy13.games.cache.Cache;

public abstract class AbstractListener implements Listener{
    private final String listenerName;
    private final String host;
    private final int port;
    private final Cache cache;

    public AbstractListener(Cache cache, String listenerName, String host, int port) {
        this.listenerName = listenerName;
        this.host = host;
        this.port = port;
        this.cache = cache;
    }
}
