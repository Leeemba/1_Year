package Common.Network.responses;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;

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
