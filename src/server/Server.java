package server;

import data.CommandPacket;
import data.ResponsePacket;
import server.managers.*;
import server.managers.ResponseManager;
import server.modules.ConnectModule;
import server.modules.ResponseModule;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    public static Generate genMan = new Generate();
    public static ParserManagerServer manPars = new ParserManagerServer();
    public static Logger logger = LoggerFactory.getLogger(Server.class);
    private static int port = 8080;
    private static ConnectModule conMud = new ConnectModule(port, 10000*60);
    private static boolean running = true;
    public static ResponseManager responseMan = new ResponseManager();
    public static CollectionManager colman = new CollectionManager();

    public static void main(String[] args) {
        conMud.run();
    }


}
