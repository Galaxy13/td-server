package com.galaxy13.games;

import com.galaxy13.games.cache.Cache;
import com.galaxy13.games.cache.GameCache;
import com.galaxy13.games.listener.Listener;
import com.galaxy13.games.listener.UDPListener;
import com.galaxy13.games.manager.GameManager;
import com.galaxy13.games.manager.Manager;

public class Main {
    public static void main(String[] args) {
        Manager gameManager = new GameManager();
        Listener networkListener = new UDPListener(gameManager.getGameCache(), "Client listener",
                "localhost", 27105);
    }
}
