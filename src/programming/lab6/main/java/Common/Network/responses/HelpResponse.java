package Common.Network.responses;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;

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
