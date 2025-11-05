package server.handler;

import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.ErrorResponse;
import common.network.responses.Response;
import server.managers.AuthManager;
import server.managers.CommandManager;

import java.sql.SQLException;

public class CommandHandler {
    private final CommandManager commandManager;
    private final AuthManager authManager;
    public CommandHandler(CommandManager commandManager,AuthManager authManager){
        this.commandManager = commandManager;
        this.authManager = authManager;
    }

    public Response handle(Request request){
        if (!request.isAuth()) {
            var user = request.getUser();
            try {
                if (user == null || !authManager.authenticate(user.getLogin(), user.getPassword())) {
                    return new ErrorResponse("bad-auth","Неверные учетные данные. Пожалуйста, войдите в свой аккаунт.",ResponseStatus.ERROR);
                }
            } catch (SQLException e) {
                return new ErrorResponse("sql-error","Невозможно выполнить запрос к БД о аутентификации пользователя.",ResponseStatus.ERROR);
            }
        }





        var command = commandManager.getCommands().get(request.getName());
        if (command == null) return new Response(null,"такой команды нет",ResponseStatus.ERROR);
        commandManager.addToHistory(command.getName());
        return command.execute(request);
    }

}
