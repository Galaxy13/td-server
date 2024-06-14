package com.galaxy13.games.tdserver.generation;

import com.galaxy13.games.tdserver.entities.Entity;

import java.lang.reflect.InvocationTargetException;

public class EntityGenerator {
    public static <T extends Entity> T generateEntity (Class<T> clazz, String name) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        return clazz.getConstructor().newInstance(name);
    }
}
