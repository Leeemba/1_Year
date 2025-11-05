package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;
import Exceptions.WrongAmountOfArgsException;
import Managers.CollectionManager;

public class CountGreaterThanAnnualTurnover extends  Command{
    private final Console console;
    private final CollectionManager collectionManager;
    private final static String NAME =  "count_greater_than_annual_turnover";
    private final static String DESCRIPTION = "выводит количество элементов, значение поля annualTurnover которых больше заданного";
    public CountGreaterThanAnnualTurnover(Console console,CollectionManager collectionManager) {
        super(NAME, DESCRIPTION);
        this.console=console;
        this.collectionManager=collectionManager;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if(args.isBlank()) throw  new WrongAmountOfArgsException();

        var aT= Integer.parseInt(args.trim());
        long temp = collectionManager.countGreatByAnnTur(aT);
        console.println(ConsoleFormat.coloring("Количество элементов коллекции, у которых годовой оборот больше заданного: "+temp,ConsoleFormat.GREEN,ConsoleFormat.BOLD));

    }
}
