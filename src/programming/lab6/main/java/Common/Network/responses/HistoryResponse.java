package Common.Network.responses;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;

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
