package client;

import client.managers.*;
import data.CommandPacket;
import data.LabWork;
import data.ResponsePacket;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;

public class Client {
    public static InputOutputManage inout = new InputOutputManage();
    public static ParserManager parser = new ParserManager();
    public static CommandManager coMan = new CommandManager();
    public static ConnectManager connectManager;

    private static String host = "127.0.0.1" ;
    private static int port = 8080;


    private static void initializeConnectionAddress(String[] hostAndPortArgs) {
        if (hostAndPortArgs.length != 2) {
            Client.inout.write("Были некорректно введены порт и хост.х");
        } else if (Integer.parseInt(hostAndPortArgs[1])<0) {
            host = hostAndPortArgs[0];
            port = Integer.parseInt(hostAndPortArgs[1]);
        }

    }

    public static void main(String[] args) {
        initializeConnectionAddress(args);
        connectManager = new ConnectManager(host,port);
        while (true){
            inout.write("Введите команду:");
            String line = inout.read().trim();
            CommandPacket commandPacket= parser.parse(line);
            run(commandPacket);

        }
    }

    public static void run(CommandPacket commandPacket){
        if (commandPacket != null ) {
            try {
                ResponsePacket responsePacket = connectManager.sendCommand(commandPacket);
                if (responsePacket.getData() != null) {
                    if (commandPacket.getType() == "show" || commandPacket.getType() == "filter_less_than_minimal_point"){
                        Map<Integer, LabWork> labWorkMap = (Map<Integer, LabWork>) responsePacket.getData();
                        for (Integer i : labWorkMap.keySet()) {
                            Client.inout.write(i + ": " + labWorkMap.get(i).toString());
                        }
                    } else {
                        Client.inout.write(responsePacket.getData().toString());
                    }
                }
            } catch (NullPointerException e ){
                Client.inout.write("Вы не были подключены к серверу.");
            }
        } else {inout.write("Такой команды не существует");}
    }
}

