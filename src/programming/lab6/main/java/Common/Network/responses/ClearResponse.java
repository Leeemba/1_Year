package Common.Network.responses;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;

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
