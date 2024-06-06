package com.galaxy13.games;

import com.galaxy13.games.ddo.ClientCommand;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Session {
    private final Integer sessionId;
    private final Map<String, Entity> sessionEntities = new ConcurrentHashMap<>();
    private Entity playerEntity;

    private String generateId(){
        return RandomStringUtils.random(8, "0123456789abcdef");
    }

    public Session(Integer sessionId) {
        this.sessionId = sessionId;
        sessionEntities.put(generateId(), new Mob("test_mob1"));
        sessionEntities.put(generateId(), new Mob("test_mob2"));
        sessionEntities.put(generateId(), new Mob("test_mob3"));
        Mob playerMob = new Mob("test_mob4");
        sessionEntities.put(generateId(), playerMob);
        playerEntity = playerMob;
    }

    private Map<String, Object> serializeEntityCoordinates(Entity entity){
        Map<String, Object> entityValues = new HashMap<>();
        entityValues.put("x", entity.getCoordinateX());
        entityValues.put("y", entity.getCoordinateY());
        entityValues.put("name", entity.getName());
        return entityValues;
    }

    private byte[] sessionState() throws IOException {
        try (ByteArrayOutputStream byteMap = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(byteMap)) {
            Map<String, List<Map<String, Object>>> outMap = new HashMap<>();
            List<Map<String, Object>> entityList = new ArrayList<>();
            for (Entity entity: sessionEntities.values()){
                entityList.add(serializeEntityCoordinates(entity));
            }
            outMap.put("entities", entityList);
            out.writeObject(outMap);
            return byteMap.toByteArray();
        }
    }

    private byte[] keyPress(String key){

    }

    public byte[] handleCommand(ClientCommand packet){
        
    }
}
