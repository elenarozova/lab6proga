package server;

import data.Generate;
import data.LabWork;
import server.comands.Save;
import server.managers.*;
import server.managers.ResponseManager;
import server.modules.ConnectModule;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    public static FileManager fileManager = new FileManager();
    public static ParserManagerServer manPars = new ParserManagerServer();
    public static Logger logger = LoggerFactory.getLogger(Server.class);
    private static int port = 8888;
    private static ConnectModule conMud;
    private static boolean running = true;
    public static ResponseManager responseMan = new ResponseManager();
    public static CollectionManager colman = new CollectionManager();

    private static String filename = "file.xml";

    public static void main(String[] args) {
        if (args.length!=0){
            for (int i = 0; i < args.length; i++) {
                try{
                    int portTest = Integer.parseInt(args[i]);
                    Server.port = portTest;
                }catch (NumberFormatException e){
                    filename = args[i];
                }
            }
        }
        fileManager.readXML(filename);
        int idi = Server.colman.getLabWork().values().stream()
                .filter(Objects::nonNull)
                .mapToInt(LabWork::getId)
                .max()
                .orElse(0);
        Generate.setIdi(idi+1);
        conMud = new ConnectModule(port, 10000*60);
        inputOutputServer();;
        conMud.run();
        Save.save(filename);
    }

    public static String getFilename() {
        return filename;
    }

    public static void inputOutputServer() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            try {
                while (true) {
                    String input = scanner.nextLine().trim();

                    if (input.equalsIgnoreCase("save")) {
                        Server.logger.info("Экстренное сохранение");
                        try {
                            Save.save(filename);
                        } catch (Exception e) {
                            Server.logger.error("Ошибка при сохранении");
                        }
                    } else if (input.equalsIgnoreCase("exit")) {
                        Save.save(filename);
                        Server.logger.error("Отключение сервера");
                        System.exit(0);
                    } else if (input.equalsIgnoreCase("help")) {
                        Server.logger.error("help - справка");
                        Server.logger.error("exit - завершить работу сервера");
                        Server.logger.error("save - сохранить коллекцию");
                    }
                }
            } catch (NoSuchElementException e) {
                Save.save(filename);
                System.exit(0);
            }
        }).start();
    }

}
