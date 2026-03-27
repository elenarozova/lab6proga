package client.commands;

import client.Client;
import client.interfaces.Comands;
import client.managers.CheckValues;
import data.CommandPacket;

public class ExecuteScript implements Comands {
    private String filename;
    @Override
    public CommandPacket implementCommand(String[] args) {
        if (args.length==0) {
            Client.inout.write("Введите название файла, из которого хотите читать скрипт:");
            filename = CheckValues.checkValuesNull("название файла, из которого хотите читать скрипт");
        } else if (args.length==1){
            filename=args[0].trim();
        } else {
            Client.inout.write("Вы ввели больше аргументов, чем надо, первый будет принят как название скрипта, а остальные будут откинуты");
            filename=args[0].trim();
        }
        Client.inout.startFileReading(filename);
        if (!Client.inout.isReadingFromFile()) {
            return null;
        }
        boolean hadError = false;
        while (Client.inout.hasNextLine()) {
            String command = Client.inout.read();
            if (command.trim().isEmpty()) {
                continue;
            }
            Client.run(Client.parser.parse(command));
            if (Client.inout.isScriptHasError()) {
                Client.inout.write("В файле была совершена ошибка ввода данных.");
                hadError = true;
                break;
            }
        }
        Client.inout.stopFileReading(filename);
        if (!hadError) {
            Client.inout.setScriptError(false);
        }
        return new CommandPacket("execute_script", null, null);
    }
}
