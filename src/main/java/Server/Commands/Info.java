package Server.Commands;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.InfoResponse;
import Common.Network.responses.Response;
import Server.ConsoleOperations.Console;
import Common.Utility.ConsoleFormat;
import Common.Utility.Hint;
import Common.Exceptions.WrongAmountOfArgsException;
import Server.Managers.CollectionManager;

public class Info extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Info(Console console,CollectionManager collectionManager) {
        super(CommandNames.INFO, CommandNames.Descriptions.DESCR_INFO);
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request)  {
    String lastInitTime = (collectionManager.getLastInitTime() == null)
            ? Hint.INITTIMEHINT.toString()
            : collectionManager.getLastInitTime().toString();
    String lastSaveTime = (collectionManager.getLastSaveTime() == null)
            ? Hint.SAVETIMEHINT.toString()
            : collectionManager.getLastSaveTime().toString();
    StringBuilder sb = new StringBuilder();

    sb.append(ConsoleFormat.coloring("Информация о коллекции:\n",ConsoleFormat.PURPLE,ConsoleFormat.BOLD,ConsoleFormat.UNDERLINE));

    sb.append(ConsoleFormat.coloring("Тип: ",ConsoleFormat.BLUE,ConsoleFormat.BOLD))
            .append(ConsoleFormat.coloring(collectionManager.collectionType() +"\n",ConsoleFormat.NONE,ConsoleFormat.BOLD));
    sb.append(ConsoleFormat.coloring("Количество элементов: ",ConsoleFormat.BLUE,ConsoleFormat.BOLD))
            .append(ConsoleFormat.coloring(collectionManager.collectionSize() +"\n",ConsoleFormat.NONE,ConsoleFormat.BOLD));
    sb.append(ConsoleFormat.coloring("Последняя дата инициализации: ",ConsoleFormat.BLUE,ConsoleFormat.BOLD))
            .append(ConsoleFormat.coloring(lastInitTime+"\n",ConsoleFormat.NONE,ConsoleFormat.BOLD));
    sb.append(ConsoleFormat.coloring("Последняя дата сохранения: ",ConsoleFormat.BLUE,ConsoleFormat.BOLD))
            .append(ConsoleFormat.coloring(lastSaveTime+"\n",ConsoleFormat.NONE,ConsoleFormat.BOLD));
    return new InfoResponse(null, ResponseStatus.OK,sb);
    }
}
