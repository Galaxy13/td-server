package com.galaxy13.games.manager;

import com.galaxy13.games.cache.Cache;
import com.galaxy13.games.cache.GameCache;
import com.galaxy13.games.cache.State;
import com.galaxy13.games.events.EventProcessor;
import com.galaxy13.games.events.Processor;
import com.galaxy13.games.field.GameField;
import com.galaxy13.games.field.TDField;
import com.galaxy13.games.objects.ObjectHandler;
import com.galaxy13.games.objects.ObjectHandlerImpl;

public class GameManager implements CacheManager, EventManager{
    private final Cache cache;
    private final Processor eventProcessor;

    public GameManager() {
        this.cache = new GameCache();
        GameField gameField = new TDField();
        ObjectHandler objectHandler = new ObjectHandlerImpl();
        this.eventProcessor = new EventProcessor(gameField, objectHandler, this.cache);
    }

    @Override
    public State getState() {
        return cache.getChanges();
    }

    @Override
    public void sendClientCommand(String command) {

    }

    @Override
    public void processEvent(Event event) {

    }
}
