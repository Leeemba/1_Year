package common.network.responses;

import common.network.CommandNames;
import common.network.ResponseStatus;

public class AuthResponse extends Response{
    private String message;

    public AuthResponse(String errorMessage, ResponseStatus status, String message){
        super(CommandNames.AUTHENTICATE,errorMessage,status);
        this.message = message;

    }

   public String getMessage(){
        return message;
   }
}
