package common.network.responses;

import common.network.CommandNames;
import common.network.ResponseStatus;

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
