package Common.Network.responses;


import Common.Network.CommandNames;
import Common.Network.ResponseStatus;

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
