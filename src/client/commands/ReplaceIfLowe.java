package client.commands;

import client.Client;
import client.interfaces.Comands;
import client.managers.CheckValues;
import data.CommandPacket;
import data.LabWork;

public class ReplaceIfLowe implements Comands {
    Integer id;
    String idi;

    @Override
    public CommandPacket implementCommand(String[] args) {

            int lenght = args.length;
            while (true) {
                if (lenght==0) {
                    Client.inout.write("Введите ключ значения,которое хотите изменить:");
                    idi = CheckValues.checkValuesNull("ключ значения,которое хотите изменить");
                } else if (lenght==1){
                    idi = args[0].trim();
                } else {
                    Client.inout.write("Было введено больше одного параметра, все превышающие параметры не учитываются");
                    idi= args[0].trim();
                }
                try {
                    id = Integer.parseInt(idi);
                    break;
                } catch (NumberFormatException e) {
                    Client.inout.write("Ключ должен быть int.");
                    lenght=0;
                }
            }
            String[] key = {String.valueOf(idi)};
            LabWork lab = new LabWork.Builder().doLab();
            return new CommandPacket("replace_if_lowe", key, lab);
    }
}
