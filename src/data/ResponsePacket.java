package data;

import java.io.Serializable;

public class ResponsePacket implements Serializable {
    private String message;
    private Object data;

    public ResponsePacket(String message, Object data){
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
