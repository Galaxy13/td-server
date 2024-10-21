package com.galaxy13.games.objects;

import com.galaxy13.games.manager.Event;

public interface ObjectProcessor {
    void processEvent(GameObject gameObject, Event event);
}
