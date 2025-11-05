package Common.Network.responses;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;

public class UpdateResponse extends Response {
    private final int id;
    public UpdateResponse(String errorMessage, ResponseStatus status, int id){
        super(CommandNames.UPDATE,errorMessage,status);
        this.id=id;
    }

    public int getId() {
        return id;
    }
}
