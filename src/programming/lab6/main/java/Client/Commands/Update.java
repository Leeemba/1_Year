package Client.Commands;

import Client.Forms.OrganizationForm;
import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.*;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.UpdateRequest;
import Common.Network.responses.UpdateResponse;
import Common.Utility.ConsoleFormat;

import java.io.IOException;

public class Update extends Command {
    private final UDPClient client;
    private final Console console;

    public Update(Console console, UDPClient client){
        super(CommandNames.UPDATE);
        this.client =client;
        this.console =console;
    }

    @Override
    public void execute(String args) throws  WrongAmountOfArgsException {
        if (args.isBlank()) throw new WrongAmountOfArgsException();
        try{
            int id = Integer.parseInt(args.trim());
            var updOrg = new OrganizationForm(console).build();

            var response = (UpdateResponse) client.sendAndReceiveCommand(new UpdateRequest(id,updOrg));
            if (response.getStatus().equals(ResponseStatus.ERROR)) throw new ResponseErrorMessageException(response.getErrorMessage());
            console.println(ConsoleFormat.coloring("Изменение объекта типа \"Organization\" с id: " +response.getId()+" прошло успешно!", ConsoleFormat.GREEN, ConsoleFormat.BOLD, ConsoleFormat.ITALIC));
        }catch (InvalidFormException e){
            console.printErr("Поля объекта невалидны,объект не создан!");
        }catch (NumberFormatException e){
            console.printErr("Id должно быть числом типа int!");
        }catch (InFileModeException e){
            console.printErr("Произошла ошибка в скриптовом режиме");
        }catch (ResponseErrorMessageException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }

    }
}
