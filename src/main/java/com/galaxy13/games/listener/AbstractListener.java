package com.galaxy13.games.listener;

import com.galaxy13.games.manager.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractListener implements Listener{
    protected final Logger logger;

    protected final String listenerName;
    protected final String host;
    protected final int port;
    protected final CacheManager cache;

    public AbstractListener(CacheManager cacheManager,
                            String listenerName,
                            String host,
                            int port, Class<? extends AbstractListener> listenerClass) {
        this.listenerName = listenerName;
        this.host = host;
        this.port = port;
        this.cache = cacheManager;
        this.logger = LoggerFactory.getLogger(listenerClass);
    }
}
