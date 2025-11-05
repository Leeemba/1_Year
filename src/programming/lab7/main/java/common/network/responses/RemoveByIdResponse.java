package common.network.responses;

import common.network.CommandNames;
import common.network.ResponseStatus;

public class RemoveByIdResponse extends Response{
    private final String message;
    public RemoveByIdResponse(String errorMessage, ResponseStatus status,String message){
        super(CommandNames.REMOVE_BY_ID,errorMessage,status);
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
