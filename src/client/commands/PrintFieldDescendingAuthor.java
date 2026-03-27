package client.commands;

import client.interfaces.Comands;
import data.CommandPacket;

public class PrintFieldDescendingAuthor implements Comands {
    @Override
    public CommandPacket implementCommand(String[] args) {
        return new CommandPacket("print_field_descending_author", null, null);
    }
}
