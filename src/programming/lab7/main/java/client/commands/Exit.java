package client.commands;

import common.exceptions.ExitPoint;
import common.exceptions.WrongAmountOfArgsException;
import common.network.CommandNames;

public class Exit extends Command {

    public Exit() {
        super(CommandNames.EXIT,CommandNames.Descriptions.DESCR_EXIT);
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException, ExitPoint {
        throw new ExitPoint();
    }
}