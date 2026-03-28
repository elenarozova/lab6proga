package server.comands;

import server.Server;

public class Save {
    public static void save(String filename){
        Server.fileManager.writeXML(filename);
    }
}
