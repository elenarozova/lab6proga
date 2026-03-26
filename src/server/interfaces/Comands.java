package server.interfaces;

import data.LabWork;
import data.ResponsePacket;

import java.net.Socket;
import java.nio.channels.SocketChannel;

public interface Comands {
    ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel);
}
