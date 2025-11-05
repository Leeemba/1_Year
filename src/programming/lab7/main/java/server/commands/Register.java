package server.commands;

import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.RegisterRequest;
import common.network.requests.Request;
import common.network.responses.RegisterResponse;
import common.network.responses.Response;
import org.postgresql.util.PSQLException;
import server.managers.AuthManager;

public class Register extends Command {
    private final AuthManager authManager;
    private final int MAX_USERNAME_LENGTH = 40;

    public Register(AuthManager authManager) {
        super(CommandNames.REGISTER, CommandNames.Descriptions.DESCR_REGISTER);
        this.authManager = authManager;
    }

    /**
     * Выполняет команду
     * @param request Запрос к серверу.
     * @return Ответ сервера.
     */
    @Override
    public Response execute(Request request) {
        var req = (RegisterRequest) request;
        var user = req.getUser();
        if (user.getLogin().length() >= MAX_USERNAME_LENGTH) {
            return new RegisterResponse( "Длина имени пользователя должна быть < " + MAX_USERNAME_LENGTH, ResponseStatus.ERROR,null);
        }

        try {
            var newUserId = authManager.register(user.getLogin(), user.getPassword());

            if (!newUserId) {
                return new RegisterResponse("Не удалось создать пользователя.",ResponseStatus.ERROR,null);
            } else {
                return new RegisterResponse(null, ResponseStatus.OK,"Пользователь с именем: " +req.getUser().getLogin() +" успешно зарегистрирован!");
            }
        } catch (PSQLException e) {
            var message = "Ошибка PostgreSQL: " + e.getMessage();
            if (e.getMessage().contains("duplicate key value violates unique constraint \"users_name_key\"")) {
                message = "Неуникальное имя пользователя! Попробуйте другое.";
            }
            return new RegisterResponse( message,ResponseStatus.ERROR,null);
        } catch (Exception e) {
            return new RegisterResponse(e.toString(),ResponseStatus.ERROR,null);
        }
    }
}
