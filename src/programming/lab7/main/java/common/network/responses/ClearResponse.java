package common.network.responses;

import common.network.CommandNames;
import common.network.ResponseStatus;

public class ClearResponse extends Response{
    private String message;
    public ClearResponse(String errorMessage, ResponseStatus status,String message){
        super(CommandNames.CLEAR,errorMessage,status);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
