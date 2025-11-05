package common.network.responses;

import common.network.CommandNames;
import common.network.ResponseStatus;

public class RegisterResponse extends Response{
    private String message;
    public RegisterResponse(String errorMessage, ResponseStatus status, String message) {
        super(CommandNames.REGISTER, errorMessage, status);
        this.message =message;
    }

    public String getMessage() {
        return message;
    }
}
