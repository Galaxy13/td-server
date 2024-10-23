package com.galaxy13.games;

import com.galaxy13.games.listener.Listener;
import com.galaxy13.games.listener.UDPListener;
import com.galaxy13.games.manager.CacheManager;
import com.galaxy13.games.manager.GameManager;

public class Main {
    public static void main(String[] args) {
        CacheManager gameManager = new GameManager();
        Listener networkListener = new UDPListener(gameManager, "Client listener",
                "localhost", 27105);
        networkListener.start();
    }
}
