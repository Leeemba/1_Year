package Client.Commands;

import Common.Exceptions.EmptyCollectionException;
import Common.Exceptions.ExitPoint;
import Common.Exceptions.WrongAmountOfArgsException;

public interface Executable {
    void execute(String args) throws  WrongAmountOfArgsException, ExitPoint;
}
