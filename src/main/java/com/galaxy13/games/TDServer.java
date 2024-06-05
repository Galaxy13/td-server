package com.galaxy13.games;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TDServer {
    private final Logger logger = LoggerFactory.getLogger(TDServer.class);
    private final int port;
    private final byte[] buffer = new byte[8];

    public TDServer(int port) {
        this.port = port;
    }

    public void start(String[] args) throws IOException {
        try (DatagramSocket serverSocket = new DatagramSocket(port);
             ExecutorService executorService = Executors.newFixedThreadPool(10)){
            logger.info("Listener started at port " + port);
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                logger.debug("Listener accepted new connection from " + clientAddress);

            }
        }
    }
}