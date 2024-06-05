package com.galaxy13.games;

import com.galaxy13.games.ddo.ClientCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Session {
    private final Integer sessionId;
    private final List<Entity> sessionEntities = new LinkedList<>();
    private final Map<EntityController, Entity> sessionEntityController = new ConcurrentHashMap<>();

    public Session(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public void handleCommand(ClientCommand packet){
        
    }
}
