package Client.Commands;

import Common.Exceptions.ExitPoint;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;

public class Exit extends Command {

    public Exit() {
        super(CommandNames.EXIT,CommandNames.Descriptions.DESCR_EXIT);
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException, ExitPoint {
        throw new ExitPoint();
    }
}