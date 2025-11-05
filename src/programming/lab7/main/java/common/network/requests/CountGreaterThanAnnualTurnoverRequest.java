package common.network.requests;

import common.models.User;
import common.network.CommandNames;

public class CountGreaterThanAnnualTurnoverRequest extends Request{
    private int aT;
    public CountGreaterThanAnnualTurnoverRequest(int aT, User user) {
        super(CommandNames.COUNT_GREATER_THAN_ANNUAL_TURNOVER,user);
        this.aT = aT;
    }

    public int getaT() {
        return aT;
    }
}
