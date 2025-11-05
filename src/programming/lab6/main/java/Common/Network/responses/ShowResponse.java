package Common.Network.responses;

import Common.Models.Organization;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;

import java.util.Collection;

public class ShowResponse extends Response {
    private Collection<Organization> result;
    public ShowResponse(String errorMessage, ResponseStatus status,Collection<Organization> result){
        super(CommandNames.SHOW,errorMessage,status);
        this.result = result;
    }

    public Collection<Organization> getResult(){
        return result;
    }


}
