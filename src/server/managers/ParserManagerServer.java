package server.managers;

import data.CommandPacket;
import data.ResponsePacket;
import server.Server;
import server.comands.*;
import server.interfaces.Comands;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ParserManagerServer {

    private Map<String, Comands> commands;

    public ParserManagerServer() {
        commands = new HashMap<>();
        commands.put("help", new Help());
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
        //commands.put("save", new Save());
        commands.put("execute_script", new ExecuteScript());

    }
    public ResponsePacket parseCommand(CommandPacket comPac, Socket client){
        String command_name = comPac.getType();

        if (this.commands.containsKey(command_name)) {
            Comands command = this.commands.get(command_name);

            ResponsePacket responsePacket = command.implementCommand(comPac.getArgs(), comPac.getLabWork(), client);
            return responsePacket;
        }else {
            Server.logger.info("Неизвестная команда");
            return new ResponsePacket("Неизвестная команда на сервере", null);
        }
    }

    public Map<String, Comands> getCommands() {
        return commands;
    }
}
