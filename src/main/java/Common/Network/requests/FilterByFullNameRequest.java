package Common.Network.requests;

import Common.Network.CommandNames;

public class FilterByFullNameRequest extends Request {
    private final String fullName;
    public FilterByFullNameRequest(String fullName){
        super(CommandNames.FILTER_BY_FULL_NAME);
        this.fullName = fullName;
    }

    public String getFullName(){
        return fullName;
    }


}
