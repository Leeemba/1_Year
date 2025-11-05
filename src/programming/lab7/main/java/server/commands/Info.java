package server.commands;

import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.InfoResponse;
import common.network.responses.Response;
import common.utility.ConsoleFormat;
import common.utility.Hint;
import server.managers.CollectionManager;

public class Info extends Command {

    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super(CommandNames.INFO, CommandNames.Descriptions.DESCR_INFO);
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
