package com.galaxy13.games.listener;

import com.galaxy13.games.listener.exception.ListenerStartException;
import com.galaxy13.games.manager.CacheManager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPListener extends AbstractListener{
    private final DatagramSocket socket;
    private final byte[] buffer;
    private final Thread listenerThread;

    public UDPListener(CacheManager cacheManager, String listenerName, String host, int port){
        super(cacheManager, listenerName, host, port, UDPListener.class);
        this.buffer = new byte[256];
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            logger.error("Socket exception while instantiating UDP Listener", e);
            throw new ListenerStartException(UDPListener.class, e);
        }
        this.listenerThread = createListenerThread();
    }

    private Thread createListenerThread(){
        return new Thread(() -> {
            while(true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    logger.error("IO exception while receiving UDP packet", e);
                }

                String received = new String(packet.getData(), 0, packet.getLength());
                String cacheResult = this.cache.sendClientCommand(received);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                byte[] byteResult = cacheResult.getBytes();
                DatagramPacket response = new DatagramPacket(byteResult, byteResult.length, address, port);
                try {
                    socket.send(response);
                } catch (IOException e) {
                    logger.error("IO exception while sending UDP response", e);
                }
            }
        });
    }

    @Override
    public void start() {
        this.listenerThread.start();
    }

    @Override
    public void stop() {
        this.createListenerThread().interrupt();
    }
}
