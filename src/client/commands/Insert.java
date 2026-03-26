package client.commands;

import client.Client;
import client.interfaces.Comands;
import client.managers.CheckValues;
import data.CommandPacket;
import data.LabWork;

public class Insert implements Comands {
    private Integer idi;

    @Override
    public CommandPacket implementCommand(String[] args) {
        if (args.length==0) {
            setKey();
        }
        else if (args.length==1) {
            try{
                idi = Integer.valueOf(args[0].trim());
            } catch (NumberFormatException e) {
                Client.inout.write("Вы ввели неподходящий ключ, он должен быть int");
                setKey();
            }
        }
        String[] key ={ String.valueOf(idi)};
        LabWork lab = new LabWork.Builder().doLab();
        return new CommandPacket("insert", key, lab);
    }

    private void setKey(){
        Client.inout.write("Введите ключ в виде числа:");
        while (true) {
            try {
                idi = Integer.valueOf(CheckValues.checkValuesNull("ключ"));
                break;
            } catch (NumberFormatException e) {
                Client.inout.write("Ключ должен быть типа int");
                Client.inout.write("Введите ключ в виде числа:");
            }
        }
    }
}
