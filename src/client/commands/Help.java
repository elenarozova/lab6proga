package client.commands;

import client.interfaces.Comands;
import data.CommandPacket;

public class Help implements Comands {

    @Override
    public CommandPacket implementCommand(String[] args) {
        return new CommandPacket("help", null, null);
    }
}
