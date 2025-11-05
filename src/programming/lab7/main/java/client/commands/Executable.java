package client.commands;

import common.exceptions.ExitPoint;
import common.exceptions.WrongAmountOfArgsException;

public interface Executable {
    void execute(String args) throws  WrongAmountOfArgsException, ExitPoint;
}
