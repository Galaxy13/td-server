package com.galaxy13.games;

import com.galaxy13.games.ddo.ClientCommand;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Session {
    private final Logger logger = LoggerFactory.getLogger(Session.class);
    private final Integer sessionId;
    private final Map<String, Entity> sessionEntities = new ConcurrentHashMap<>();
    private Movable playerEntity;

    public Session(Integer sessionId) {
        this.sessionId = sessionId;
        createNewMob("test_mob1");
        createNewMob("test_mob2");
        createNewMob("test_mob3");
        Mob playerMob = new Mob("test_mob4");
        sessionEntities.put(generateId(), playerMob);
        playerEntity = playerMob;
        logger.debug("Player entity created");
        logger.info("New session created. ID: {}", sessionId);
    }

    private String generateId() {
        logger.trace("ID entity generation requested");
        String id = RandomStringUtils.random(8, "0123456789abcdef");
        logger.trace("ID entity generated: {}", id);
        return id;
    }

    private void createNewMob(String entityName) {
        logger.trace("Creating new mob {}", entityName);
        synchronized (sessionEntities) {
            sessionEntities.put(generateId(), new Mob(entityName));
        }
        logger.debug("Created new mob {}", entityName);
    }

    private Map<String, Object> serializeEntityCoordinates(Entity entity){
        logger.trace("Serializing entity coordinates...");
        Map<String, Object> entityValues = new HashMap<>();
        entityValues.put("x", entity.getCoordinateX());
        entityValues.put("y", entity.getCoordinateY());
        entityValues.put("name", entity.getName());
        logger.trace("Serialization complete");
        return entityValues;
    }

    private byte[] sessionState() throws IOException {
        logger.debug("Session state requested");
        try (ByteArrayOutputStream byteMap = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(byteMap)) {
            Map<String, List<Map<String, Object>>> outMap = new HashMap<>();
            List<Map<String, Object>> entityList = new ArrayList<>();
            for (Entity entity: sessionEntities.values()){
                entityList.add(serializeEntityCoordinates(entity));
            }
            outMap.put("entities", entityList);
            out.writeObject(outMap);
            logger.trace("Session state request completed");
            return byteMap.toByteArray();
        }
    }

    private byte[] keyPress(Integer key) {
        logger.debug("Key press requested");
        Directions direction;
        switch (key) {
            case 87 -> direction = Directions.UP;
            case 83 -> direction = Directions.DOWN;
            case 65 -> direction = Directions.LEFT;
            case 68 -> direction = Directions.RIGHT;
            default -> {
                logger.debug("Invalid key press");
                return new byte[0];
            }
        }
        logger.trace("Key code parsing: OK");
        playerEntity.move(direction.getSpeedX(), direction.getSpeedY());
        logger.trace("Player entity moved to {}", direction);
        return "0".getBytes(StandardCharsets.UTF_8);
    }

    private byte[] switchEntity() {
        logger.debug("Switch entity requested");
        Random rand = new Random();
        Object[] entities = sessionEntities.values().toArray();
        if (entities.length < 2) {
            logger.debug("No entities to switch");
            return "12".getBytes(StandardCharsets.UTF_8); // no entities to switch
        }
        Movable movableEntity = (Movable) entities[rand.nextInt(entities.length)];
        if (movableEntity.equals(playerEntity)) {
            logger.debug("Same entity as player chosen");
            return switchEntity();
        } else {
            playerEntity = movableEntity;
            logger.trace("Player entity switched to {}", movableEntity);
            return "0".getBytes(StandardCharsets.UTF_8);
        }
    }


    public byte[] handleCommand(ClientCommand packet) throws IOException {
        logger.trace("Handling command {}", packet);
        switch (packet.getOperation()) {
            case STATE -> {
                return sessionState();
            }
            case KEY -> {
                try {
                    Integer keyCode = Integer.valueOf(packet.getMessage().split(" ")[0]);
                    return keyPress(keyCode);
                } catch (NumberFormatException e) {
                    logger.debug("Key code in packet is not int number");
                    return "11".getBytes(StandardCharsets.UTF_8);
                }
            }
            case SWITCH -> {
                return switchEntity();
            }
            default -> {
                logger.debug("Invalid operation {}", packet.getOperation());
                return "10".getBytes(StandardCharsets.UTF_8);
            }
        }
    }
}
