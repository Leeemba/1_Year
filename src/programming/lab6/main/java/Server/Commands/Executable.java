package Server.Commands;

import Common.Network.requests.Request;
import Common.Network.responses.Response;

public interface Executable {
    Response execute(Request request);
}
