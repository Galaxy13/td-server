package com.galaxy13.games.tdserver.ddo;

import com.galaxy13.games.tdserver.client.ClientOperations;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class ClientCommand {
    private final String message;
    private final ClientOperations operation;
    private final InetAddress ip;

    private ClientOperations getOperationType(String message) throws UnsupportedOperationException{
        String msg = message.split(" ")[0];
        return switch (msg) {
            case "/start" -> ClientOperations.START;
            case "/switch" -> ClientOperations.SWITCH;
            case "/state" -> ClientOperations.STATE;
            case "/key" -> ClientOperations.KEY;
            default -> throw new UnsupportedOperationException();
        };
    }

    public ClientCommand(DatagramPacket packet) {
        this.message = new String(packet.getData(), packet.getOffset(), packet.getLength());
        this.operation = getOperationType(this.message);
        this.ip = packet.getAddress();
    }

    public String getMessage() {
        return message;
    }

    public InetAddress getIp() {
        return ip;
    }

    public ClientOperations getOperation() {
        return operation;
    }
}
