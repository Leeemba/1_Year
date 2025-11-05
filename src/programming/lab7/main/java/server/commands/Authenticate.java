package server.commands;

import common.network.ResponseStatus;
import common.network.requests.AuthRequest;
import common.network.requests.Request;
import common.network.responses.AuthResponse;
import common.network.responses.Response;
import server.managers.AuthManager;

public class Authenticate extends Command {
    private final AuthManager authManager;

    public Authenticate(AuthManager authManager) {
        super("authenticate", "аутентифицировать пользователя по логину и паролю");
        this.authManager = authManager;
    }

    /**
     * Выполняет команду
     * @param request Запрос к серверу.
     * @return Ответ сервера.
     */
    @Override
    public Response execute(Request request) {
        var req = (AuthRequest) request;
        var user = req.getUser();
        try {
            var isUser = authManager.authenticate(user.getLogin(), user.getPassword());

            if (!isUser) {
                return new AuthResponse("Такого пользователя не существует.", ResponseStatus.ERROR,null);
            } else {
                return new AuthResponse(null, ResponseStatus.OK,"Пользователь "+user.getLogin()+" успешно найден!");
            }
        } catch (Exception e) {
            return new AuthResponse(e.toString(),ResponseStatus.ERROR,null);
        }
    }
}