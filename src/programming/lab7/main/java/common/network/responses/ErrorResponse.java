package common.network.responses;

import common.network.ResponseStatus;

public class ErrorResponse extends Response{
    public ErrorResponse(String name, String errorMessage, ResponseStatus status){
        super(name,errorMessage,status);
    }


    @Override
    public boolean isErrorResponse() {
        return true;
    }
}
