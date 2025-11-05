package Commands;

import Exceptions.EmptyCollectionException;
import Exceptions.ExitPoint;
import Exceptions.WrongAmountOfArgsException;

public interface Executable {
    void execute(String args) throws EmptyCollectionException, WrongAmountOfArgsException, ExitPoint;
}
