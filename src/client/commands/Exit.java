package client.commands;

import client.interfaces.Comands;
import data.CommandPacket;

public class Exit implements Comands {
    @Override
    public CommandPacket implementCommand(String[] args) {
        System.exit(0);
        return null;
    }
}
