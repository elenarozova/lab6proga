package client.commands;

import client.Client;
import client.interfaces.Comands;
import client.managers.CheckValues;
import data.CommandPacket;
import data.LabWork;

public class Update implements Comands {
    String keyStr;

    @Override
    public CommandPacket implementCommand(String[] args) {
        if (args.length==0) {
            Client.inout.write("Введите ключ объекта, который хотите обновить:");
            keyStr = CheckValues.checkValuesNull("ключ объекта, который хотите обновить:");
        } else if (args.length==1){
            keyStr = args[0].trim();
        } else {
            Client.inout.write("Было введено больше одного параметра, все превышающие параметры не учитываются");
            keyStr= args[0].trim();
        }
        try {
            Integer key = Integer.parseInt(keyStr);
            String[] keys = {(String) keyStr};
            LabWork labWork = new LabWork.Builder().doLab();
            return new CommandPacket("update",keys, labWork);
        } catch (NumberFormatException e) {
            Client.inout.write("Ключи являются типом int");
            return null;
        }
    }
}
