package Common.Network.responses;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;

public class RemoveFirstResponse extends Response{
    private final String message;
    public RemoveFirstResponse(String errorMessage, ResponseStatus status,String message){
        super(CommandNames.REMOVE_FIRST,errorMessage,status);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
