package com.galaxy13.games.tdserver.generation;

import com.galaxy13.games.tdserver.Session;
import org.apache.commons.lang3.RandomStringUtils;

public class SessionGeneration {
    public static String generateSessionId() {
        return RandomStringUtils.random(8, "0123456789abcdef");
    }

}
