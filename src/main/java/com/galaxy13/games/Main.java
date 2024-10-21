package com.galaxy13.games;

import com.galaxy13.games.cache.Cache;
import com.galaxy13.games.cache.GameCache;
import com.galaxy13.games.listener.UDPListener;

public class Main {
    public static void main(String[] args) {
        Cache gameCache = new GameCache();
        var networkListener = new UDPListener(gameCache, "Client listener",
                "localhost", 27105);
    }
}
