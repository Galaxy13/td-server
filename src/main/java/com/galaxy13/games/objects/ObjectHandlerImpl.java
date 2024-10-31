package com.galaxy13.games.objects;

import com.galaxy13.games.field.GameField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

public class ObjectHandlerImpl implements ObjectHandler {
    private static final Logger logger = LoggerFactory.getLogger(ObjectHandlerImpl.class);
    private final Map<UUID, GameObject> objects;

    public ObjectHandlerImpl() {
        objects = new HashMap<>();
        logger.info("Creating new object handler: " + this);
    }

    @Override
    public void createNewMob(String name, GameField field) {
        Mob newMob = Mob.create(name, field);
        objects.put(newMob.getId(), newMob);
        logger.info("created Mob {} with id {}", name, newMob.getId());
    }

    @Override
    public void changeObjectPosition(UUID id, double x, double y) {
        GameObject gameObject = objects.get(id);
        if (gameObject instanceof Position position) {
            position.setX(x);
            position.setY(y);
            logger.info("{} changed position to {}", id, position);
        } else {
            logger.warn("Object manager tries to change position of object, which is not impl Position");
        }
    }
}
