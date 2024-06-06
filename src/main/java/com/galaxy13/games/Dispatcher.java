package com.galaxy13.games;

import com.galaxy13.games.ddo.ClientCommand;
import com.galaxy13.games.exceptions.NoSessionException;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private final Map<InetAddress, Session> sessionMap = new HashMap<>();
    private int globalCounter = 0;

    public Dispatcher(){

    }

    public byte[] handleDatagram(DatagramPacket packet) throws UnsupportedOperationException, NoSessionException{
        ClientCommand command = new ClientCommand(packet);
        if (sessionMap.containsKey(command.getIp())){
            Session clientSession = sessionMap.get(packet.getAddress());
            return clientSession.handleCommand(command);
        } else {
            if (command.getOperation().equals(ClientOperations.START)){
                sessionMap.put(command.getIp(), new Session(globalCounter));
                globalCounter += 1;
                return "0".getBytes(StandardCharsets.UTF_8);
            } else {
                throw new NoSessionException();
            }
        }
    }
}
