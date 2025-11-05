package common.network.responses;

import common.network.CommandNames;
import common.network.ResponseStatus;

public class InfoResponse extends Response {
    private final StringBuilder info;
    public InfoResponse(String errorMessage, ResponseStatus status,StringBuilder info){
        super(CommandNames.INFO,errorMessage,status);
        this.info = info;
    }

    public StringBuilder getInfo() {
        return info;
    }

}
