package com.galaxy13.games.events;

import com.galaxy13.games.cache.Cache;
import com.galaxy13.games.field.GameField;
import com.galaxy13.games.manager.Event;
import com.galaxy13.games.objects.ObjectHandler;

public class EventProcessor implements Processor{
    private final GameField gameField;
    private final ObjectHandler objectHandler;
    private final Cache cache;

    public EventProcessor(GameField gameField, ObjectHandler objectHandler, Cache cache) {
        this.gameField = gameField;
        this.objectHandler = objectHandler;
        this.cache = cache;
    }

    @Override
    public void process(Event event) {

    }
}
