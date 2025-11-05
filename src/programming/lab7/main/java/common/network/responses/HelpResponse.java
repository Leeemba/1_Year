package common.network.responses;

import common.network.CommandNames;
import common.network.ResponseStatus;

import java.util.Map;

public class HelpResponse extends  Response {
    private Map<String,String> helpInfo;
    public HelpResponse(String errorMessage, ResponseStatus status,Map<String,String> helpInfo){
        super(CommandNames.HELP,errorMessage,status);
        this.helpInfo = helpInfo;
    }

    public Map<String, String> getHelpInfo() {
        return helpInfo;
    }
}
