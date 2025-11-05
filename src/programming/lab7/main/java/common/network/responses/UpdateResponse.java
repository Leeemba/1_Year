package common.network.responses;

import common.network.CommandNames;
import common.network.ResponseStatus;

public class UpdateResponse extends Response {
    private final String message;
    public UpdateResponse(String errorMessage, ResponseStatus status,String message){
        super(CommandNames.UPDATE,errorMessage,status);
        this.message = message;
    }

    public String getMessage() {
       return message;
    }
}
