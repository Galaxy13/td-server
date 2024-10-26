package com.galaxy13.games.listener.exception;

public class ListenerStartException extends RuntimeException{
    public ListenerStartException(Class<?> listenerClass, Exception e) {
        super("Error starting network listener: " + listenerClass.getSimpleName(), e);
    }
}
