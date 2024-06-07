package com.galaxy13.games;

import com.galaxy13.games.exceptions.NoSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TDServer {
    private final Logger logger = LoggerFactory.getLogger(TDServer.class);
    private final int port;
    private final byte[] buffer = new byte[8];
    private final Dispatcher packetDispatcher = new Dispatcher();

    public TDServer(int port) {
        this.port = port;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() throws IOException {
        try (DatagramSocket serverSocket = new DatagramSocket(port);
             ExecutorService executorService = Executors.newFixedThreadPool(10)){
            logger.info("Listener started at port " + port);
            while (true) {
                final DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);
                executorService.execute(() -> {
                    InetAddress clientAddress = packet.getAddress();
                    logger.trace("Client address: " + clientAddress);
                    int clientPort = packet.getPort();
                    logger.trace("Client port: " + clientPort);
                    logger.debug("Listener accepted new connection from " + clientAddress);
                    byte[] answer;
                    try {
                        answer = packetDispatcher.handleDatagram(packet);
                        logger.trace("Answer received: " + new String(answer, StandardCharsets.UTF_8));
                    } catch (NoSessionException e){
                        logger.debug("Client requested command before starting session");
                        answer = "10".getBytes(StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        logger.warn("State serialization failed", e);
                        answer = "100".getBytes(StandardCharsets.UTF_8); // internal error
                    }
                    DatagramPacket responsePacket = new DatagramPacket(answer, answer.length, clientAddress, clientPort);
                    logger.trace("Server response from " + clientAddress + ": " + new String(answer, StandardCharsets.UTF_8));
                    try {
                        serverSocket.send(responsePacket);
                        logger.debug("Listener sent response to " + clientAddress);
                    } catch (IOException e) {
                        logger.warn("Error sending response", e);
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
}