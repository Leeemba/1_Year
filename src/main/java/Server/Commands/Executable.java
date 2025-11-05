package Server.Commands;

import Common.Exceptions.EmptyCollectionException;
import Common.Exceptions.ExitPoint;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.requests.Request;
import Common.Network.responses.Response;

public interface Executable {
    Response execute(Request request);
}
