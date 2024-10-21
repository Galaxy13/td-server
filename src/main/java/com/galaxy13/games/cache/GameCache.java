package com.galaxy13.games.cache;

import com.galaxy13.games.manager.Event;


public class GameCache implements Cache{
    private final State sessionState;

    public GameCache() {
        sessionState = new GameState();
    }

    @Override
    public State getChanges() {
        return null;
    }

    @Override
    public void putEvent(Event event) {

    }
}
