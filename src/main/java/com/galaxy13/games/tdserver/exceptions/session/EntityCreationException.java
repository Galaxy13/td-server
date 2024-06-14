package com.galaxy13.games.tdserver.exceptions.session;

public class EntityCreationException extends Exception{
    public EntityCreationException(String entityName, Exception e){
        super(String.format("Error %s while creating new entity %s", e.getMessage(), entityName));
    }
}
