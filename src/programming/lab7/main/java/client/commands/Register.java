package client.commands;

import client.forms.RegisterForm;
import client.network.UDPClient;
import client.utility.Console;
import client.utility.SessionHandler;
import common.exceptions.*;
import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.RegisterRequest;
import common.network.responses.RegisterResponse;
import common.utility.ConsoleFormat;

import java.io.IOException;

public class Register extends Command{
    private Console console;
    private UDPClient client;
    public Register(Console console, UDPClient client){
        super(CommandNames.REGISTER);
        this.console = console;
        this.client=client;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        try {
            var user = new RegisterForm(console).build();
            var response =(RegisterResponse) client.sendAndReceiveCommand(new RegisterRequest(user));
            if (response.getStatus().equals(ResponseStatus.ERROR)) throw new ResponseErrorMessageException(response.getErrorMessage());
            console.println(ConsoleFormat.coloring(response.getMessage(),ConsoleFormat.GREEN,ConsoleFormat.BOLD));
            SessionHandler.getInstance().setCurrentUser(user);
        } catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }catch (ResponseErrorMessageException | ErrorResponseException e){
            console.printErr(e.getMessage());
        } catch (InFileModeException e) {
            console.printErr("Произошла ошибка в скриптовом режиме");
        } catch (InvalidFormException e) {
            console.printErr("Поля объекта невалидны,объект не создан!");
        }
    }
}
