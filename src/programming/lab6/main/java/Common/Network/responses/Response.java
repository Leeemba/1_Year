package Common.Network.responses;

import Common.Network.ResponseStatus;

import java.io.Serializable;

public class Response implements Serializable {

    private String name;
    private String errorMessage;
    private final ResponseStatus status;


    public Response(ResponseStatus status){
        this.status = status;
    }

    public Response(String name,String errorMessage,ResponseStatus status){
        this.name = name;
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public String getName(){
        return name;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ResponseStatus getStatus() {
        return status;
    }


}
