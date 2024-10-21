package com.galaxy13.games.cache;


import com.galaxy13.games.manager.Event;

public interface Cache {
    State getChanges();

    void putEvent(Event event);
}
