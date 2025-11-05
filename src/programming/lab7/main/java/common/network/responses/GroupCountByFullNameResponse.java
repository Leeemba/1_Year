package common.network.responses;

import common.network.CommandNames;
import common.network.ResponseStatus;

import java.util.Map;

public class GroupCountByFullNameResponse extends  Response{
    Map<String,Long> result;
    public GroupCountByFullNameResponse(Map<String,Long> result, String errorMessage, ResponseStatus status){
        super(CommandNames.GROUP_COUNTING_BY_FULL_NAME,errorMessage,status);
        this.result = result;
    }

    public Map<String,Long> getResult(){
        return result;
    }
}
