package common.network.requests;

import common.models.User;
import common.network.CommandNames;

public class FilterByFullNameRequest extends Request {
    private final String fullName;
    public FilterByFullNameRequest(String fullName, User user){
        super(CommandNames.FILTER_BY_FULL_NAME,user);
        this.fullName = fullName;
    }

    public String getFullName(){
        return fullName;
    }


}
