package data;

import java.io.Serializable;

public class CommandPacket  implements Serializable {
    private String type;
    private String[] args;
    private LabWork labWork;
    public CommandPacket(String type, String[] args, LabWork labWork){
        this.args = args;
        this.labWork = labWork;
        this.type = type;
    }

    public String getType(){return type;}

    public String[] getArgs() {
        return args;
    }

    public LabWork getLabWork() {
        return labWork;
    }
}
