package client.commands;

import client.Client;
import client.managers.CheckValues;
import data.CommandPacket;

public class FilterLessThanMinimalPoint implements client.interfaces.Comands {
    Double minPoint;
    String testMin;

    @Override
    public CommandPacket implementCommand(String[] args) {
        int lenght = args.length;
        while (true) {
            if (lenght == 0) {
                Client.inout.write("Введите значения минимального значения:");
                testMin = CheckValues.checkValuesNull("минимального значения");
            } else if (lenght == 1) {
                testMin = args[0].trim();
            } else {
                Client.inout.write("Было введено больше одного параметра, все превышающие параметры не учитываются");
                testMin = args[0].trim();
            }

            try {
                minPoint = Double.parseDouble(testMin.replace(",", "."));
                String[] minpoint = {String.valueOf(minPoint)};
                return new CommandPacket("filter_less_than_minimal_point", minpoint, null);
            } catch (NumberFormatException e) {
                Client.inout.write("Значение должно быть double.");
                lenght=0;
            }
        }
    }
}