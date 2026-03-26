package client.commands;

import client.Client;
import client.interfaces.Comands;
import client.managers.CheckValues;
import data.CommandPacket;

public class RemoveGreaterKey implements Comands {
    String testMin;
    @Override
    public CommandPacket implementCommand(String[] args) {
        int lenght = args.length;
        if (lenght == 0) {
            Client.inout.write("Введите значение элемента, после которого все остальные необходимо удалить:");
            testMin = CheckValues.checkValuesNull("значение элемента, после которого все остальные необходимо удалить:");
        } else if (lenght == 1){
            testMin = args[0].trim();
        } else {
            Client.inout.write("Было введено больше одного параметра, все превышающие параметры не учитываются");
            testMin=args[0].trim();
        }
        try {
            int id = Integer.parseInt(testMin);
            String[] key = {String.valueOf(id)};
            return new CommandPacket("remove_greater_key", key,null);
        } catch (NumberFormatException e){
            Client.inout.write("Вы ввели не ключ, он должен быть int. Команда не выполнена, введите её снова.");
        }
        return null;
    }
}
