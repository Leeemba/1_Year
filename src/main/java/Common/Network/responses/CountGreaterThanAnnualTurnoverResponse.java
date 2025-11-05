package Common.Network.responses;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;

public class CountGreaterThanAnnualTurnoverResponse extends  Response{
    private long count;
    public CountGreaterThanAnnualTurnoverResponse(String errorMessage, ResponseStatus status,long count ){
        super(CommandNames.COUNT_GREATER_THAN_ANNUAL_TURNOVER,errorMessage,status);
        this.count = count;
    }

    public long getCount() {
        return count;
    }
}
