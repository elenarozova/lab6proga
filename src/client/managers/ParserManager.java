package client.managers;

import client.Client;
import client.commands.*;
import client.interfaces.Comands;
import data.CommandPacket;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParserManager {
    private Map<String, Comands> commands;

    public ParserManager() {
        commands = new HashMap<>();
        commands.put("help", new Help());
        commands.put("exit", new Exit());
        commands.put("insert", new Insert());
        commands.put("show", new Show());
        commands.put("clear", new Clear());
        commands.put("update", new Update());
        commands.put("remove_key", new RemoveKey());
        commands.put("info", new Info());
        commands.put("remove_greater_key", new RemoveGreaterKey());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("replace_if_lowe", new ReplaceIfLowe());
        commands.put("filter_less_than_minimal_point", new FilterLessThanMinimalPoint());
        commands.put("print_unique_author", new PrintUniqueAuthor());
        commands.put("print_field_descending_author", new PrintFieldDescendingAuthor());
        commands.put("execute_script", new ExecuteScript());
    }

    /**
     * Выполняет парсинг и обработку введённой пользователем команды.
     * Разбивает входную строку по пробелам, извлекает имя команды и выполняет соответствующую команду.
     * Если команда не найдена или при её выполнении возникла ошибка, возвращает false
     *
     * @param line строка, введённая пользователем (содержит имя команды и, возможно, аргументы)
     * @return true, если команда успешно найдена и выполнена, иначе false
     * @see Program#inout
     * @see Comands#implementCommand(String[] args)
     */

    public CommandPacket parse(String line) {
        String[] command = line.trim().replaceAll("\\s+", " ").split(" ");
        if (commands.containsKey(command[0])) {
            Comands com = commands.get(command[0]);
            try {
                CommandPacket commandPacket = com.implementCommand(Arrays.copyOfRange(command,1,command.length));
                return commandPacket;
            } catch (Exception e) {
                Client.inout.write("Ошибка при выполнении команды");
                Client.inout.setScriptError(true);
                return null;
            }
        } else {
            Client.inout.setScriptError(true);
            return null;
        }
    }

    public Map<String, Comands> getCommands() {
        return commands;
    }
}
