package client.commands;

import client.Client;
import client.interfaces.Comands;
import data.CommandPacket;
import data.LabWork;

public class RemoveGreater implements Comands {
    @Override
    public CommandPacket implementCommand(String[] args) {
        if (args.length == 0) {
            Client.inout.write("Введите элемент, с которым будут сравниваться другие");
            LabWork lab = new LabWork.Builder().doLab();
            return new CommandPacket("remove_greater", null, lab);
        } else {
            Client.inout.write("У этой команды нет параметров");
        }
        return null;
    }
}
