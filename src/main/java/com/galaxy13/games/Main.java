package com.galaxy13.games;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 8089;
        TDServer server = new TDServer(port);
        server.start();
    }
}
