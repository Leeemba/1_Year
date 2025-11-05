package client.commands;

import client.forms.OrganizationForm;
import client.network.UDPClient;
import client.utility.SessionHandler;
import common.exceptions.*;
import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.AddRequest;
import common.network.responses.AddResponse;
import common.utility.ConsoleFormat;
import client.utility.Console;

import java.io.IOException;


public class Add extends Command implements Executable {

    private final UDPClient client;
    private final Console console;


    public Add(UDPClient client, Console console) {
        super(CommandNames.ADD);
        this.client = client;
        this.console = console;
    }


    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        console.println(ConsoleFormat.coloring("Создание объекта типа \"Organization\"",ConsoleFormat.PURPLE,ConsoleFormat.BOLD));
        try {
            var newObj = (new OrganizationForm(console).build());
            var response = (AddResponse) client.sendAndReceiveCommand(new AddRequest(newObj, SessionHandler.getInstance().getCurrentUser()));
            if (response.getStatus().equals(ResponseStatus.ERROR)){
                throw new ResponseErrorMessageException(response.getErrorMessage());
            }
            console.println(ConsoleFormat.coloring("Создание объекта типа \"Organization\" прошло успешно!Создан объект с id:"+response.getNewId(), ConsoleFormat.GREEN, ConsoleFormat.BOLD, ConsoleFormat.ITALIC));
        }catch (InvalidFormException e){
            console.printErr("Поля объекта невалидны,объект не создан!");
        }catch (InFileModeException e){
            console.printErr("Произошла ошибка в скриптовом режиме");
        }catch (ResponseErrorMessageException | ErrorResponseException e ){
            console.printErr(e.getMessage());
        } catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }

    }
}
