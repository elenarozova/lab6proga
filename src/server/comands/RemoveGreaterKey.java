package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.Server;
import server.interfaces.Comands;

import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RemoveGreaterKey implements Comands {
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        Integer key = Integer.parseInt(args[0]);
        TreeMap<Integer,LabWork> labaW  = Server.colman.getLabWork();
        TreeMap<Integer,LabWork>  filter = labaW.entrySet().stream()
                .filter(l -> l.getKey()>key)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1,v2)->v1,
                        TreeMap::new
                ));
        Server.colman.setLabWork(filter);
        return new ResponsePacket("Были удалены все элементы c ключом, превышающий данный", null);
    }
}
