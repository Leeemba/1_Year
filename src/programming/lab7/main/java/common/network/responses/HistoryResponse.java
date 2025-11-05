package common.network.responses;

import common.network.CommandNames;
import common.network.ResponseStatus;

public class HistoryResponse extends Response{
    private StringBuilder history;
    public HistoryResponse(String errorMessage, ResponseStatus status,StringBuilder history){
        super(CommandNames.HISTORY,errorMessage,status);
        this.history = history;
    }

    public StringBuilder getHistory() {
        return history;
    }
}
