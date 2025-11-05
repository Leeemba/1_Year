package Common.Network.responses;

import Common.Models.Organization;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;

import java.util.Collection;

public class FilterByFullNameResponse extends  Response {
    private Collection<Organization> result;
    public FilterByFullNameResponse(Collection<Organization> result, String errorMessage, ResponseStatus status){
        super(CommandNames.FILTER_BY_FULL_NAME,errorMessage,status);
        this.result = result;
    }
    public Collection<Organization> getResult(){
        return result;
    }

}
