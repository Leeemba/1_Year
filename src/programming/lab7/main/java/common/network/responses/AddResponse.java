package common.network.responses;


import common.network.CommandNames;
import common.network.ResponseStatus;

public class AddResponse extends Response {
    private final int newId;
    public AddResponse(int newId, String errorMessage, ResponseStatus status){
        super(CommandNames.ADD,errorMessage,status);
        this.newId = newId;
    }

    public int getNewId(){
        return newId;
    }
}
