package Commands;

import Exceptions.ExitPoint;
import Exceptions.WrongAmountOfArgsException;

public class Exit extends Command{
    private final static String NAME = "exit";
    private final static String DESCRIPTION ="завершает программу (без сохранения в файл)";
    public Exit() {
        super(NAME,DESCRIPTION);
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException,ExitPoint {
    throw new ExitPoint();
    }
}
