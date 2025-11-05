package Common.Network.requests;

import Common.Network.CommandNames;

public class CountGreaterThanAnnualTurnoverRequest extends Request{
    private int aT;
    public CountGreaterThanAnnualTurnoverRequest(int aT) {
        super(CommandNames.COUNT_GREATER_THAN_ANNUAL_TURNOVER);
        this.aT = aT;
    }

    public int getaT() {
        return aT;
    }
}
