package com.galaxy13.games.listener;

import com.galaxy13.games.manager.CacheManager;

public class TCPListener extends AbstractListener{
    public TCPListener(CacheManager cacheManager, String listenerName, String host, int port) {
        super(cacheManager, listenerName, host, port);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
