package client.commands;

import client.Client;
import client.interfaces.Comands;
import data.CommandPacket;

public class PrintUniqueAuthor implements Comands {
    @Override
    public CommandPacket implementCommand(String[] args) {
        if (args.length==0) {
            return new CommandPacket("print_unique_author",null, null);
        } else {
            Client.inout.write("У этой команды нет параметров");
        }
        return null;
    }
}
