package Client.Commands;

import Client.Forms.OrganizationForm;
import Client.network.UDPClient;
import Common.Exceptions.InFileModeException;
import Common.Exceptions.InvalidFormException;
import Common.Exceptions.ResponseErrorMessageException;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.AddRequest;
import Common.Network.responses.AddResponse;
import Common.Utility.ConsoleFormat;
import Client.utility.Console;

import java.io.IOException;


public class Add extends  Command implements Executable {

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
            var response = (AddResponse) client.sendAndReceiveCommand(new AddRequest(newObj));
            if (response.getStatus().equals(ResponseStatus.ERROR)){
                throw new ResponseErrorMessageException(response.getErrorMessage());
            }
            console.println(ConsoleFormat.coloring("Создание объекта типа \"Organization\" прошло успешно!Создан объект с id:"+response.getNewId(), ConsoleFormat.GREEN, ConsoleFormat.BOLD, ConsoleFormat.ITALIC));
        }catch (InvalidFormException e){
            console.printErr("Поля объекта невалидны,объект не создан!");
        }catch (InFileModeException e){
            console.printErr("Произошла ошибка в скриптовом режиме");
        }catch (ResponseErrorMessageException e ){
            console.printErr(e.getMessage());
        } catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }

    }
}
