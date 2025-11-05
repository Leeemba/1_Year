package client.commands;

import client.forms.AuthenticateForm;
import client.network.UDPClient;
import client.utility.Console;
import client.utility.SessionHandler;
import common.exceptions.*;
import common.exceptions.ResponseErrorMessageException;
import common.exceptions.WrongAmountOfArgsException;
import common.network.ResponseStatus;
import common.network.CommandNames;
import common.network.requests.AuthRequest;
import common.network.responses.AuthResponse;
import common.utility.ConsoleFormat;


import java.io.IOException;

public class Authenticate extends Command{
    private Console console;
    private UDPClient client;

    public Authenticate(Console console, UDPClient client){
        super(CommandNames.AUTHENTICATE);
        this.console = console;
        this.client = client;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        try {
            var user = new AuthenticateForm(console).build();
            var response =(AuthResponse) client.sendAndReceiveCommand(new AuthRequest(user));
            if(response.getStatus().equals(ResponseStatus.ERROR)) throw  new ResponseErrorMessageException(response.getErrorMessage());
            console.println(ConsoleFormat.coloring(response.getMessage(),ConsoleFormat.GREEN,ConsoleFormat.BOLD));
            console.println(ConsoleFormat.coloring("Добро пожаловать!Вы в системе:)",ConsoleFormat.WHITE,ConsoleFormat.BOLD));
            SessionHandler.getInstance().setCurrentUser(user);
        } catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }catch (ResponseErrorMessageException|ErrorResponseException e){
            console.printErr(e.getMessage());
        } catch (InFileModeException e) {
            console.printErr("Произошла ошибка в скриптовом режиме");
        } catch (InvalidFormException e) {
            console.printErr("Поля объекта невалидны,объект не создан!");
        }
    }
}
