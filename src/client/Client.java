package client;

import client.managers.*;
import data.CommandPacket;
import data.LabWork;
import data.ResponsePacket;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Client {
    public static InputOutputManage inout = new InputOutputManage();
    public static ParserManager parser = new ParserManager();
    public static CommandManager coMan = new CommandManager();
    public static ConnectManager connectManager;

    private static String host = "127.0.0.1" ;
    private static int port = 8888;


    private static void initializeConnectionAddress(String[] hostAndPortArgs) {
        Client.inout.write(hostAndPortArgs.toString());
        if (hostAndPortArgs.length ==0) {
            Client.inout.write("Не были введены порт и хост");
        } else if (hostAndPortArgs.length == 1) {
            try {
                port = Integer.parseInt(hostAndPortArgs[0]);
            } catch (NumberFormatException e) {
                host = hostAndPortArgs[0];
            }
        } else {
            try {
                port = Integer.parseInt(hostAndPortArgs[0]);
                host = hostAndPortArgs[1];
            } catch (NumberFormatException e) {
                try{
                    port = Integer.parseInt(hostAndPortArgs[1]);
                    host = hostAndPortArgs[0];
                } catch (NumberFormatException ex) {
                    Client.inout.write("Неправильно были введены порт и хост, выбрано стандартное значение - " + port + ", "+ host);
                }

            }
        }
        Client.inout.write("Bыбрано стандартное значение - " + port + ", "+ host);

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

