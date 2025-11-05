package client.commands;

import client.forms.OrganizationForm;
import client.network.UDPClient;
import client.utility.Console;
import client.utility.SessionHandler;
import common.exceptions.*;
import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.UpdateRequest;
import common.network.responses.UpdateResponse;
import common.utility.ConsoleFormat;

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


            var response = (UpdateResponse) client.sendAndReceiveCommand(new UpdateRequest(id,null, SessionHandler.getInstance().getCurrentUser()));
            if (response.getStatus().equals(ResponseStatus.ERROR)) {
                throw new ResponseErrorMessageException(response.getErrorMessage());
            }else{
                console.println(ConsoleFormat.coloring(response.getMessage(), ConsoleFormat.GREEN, ConsoleFormat.BOLD, ConsoleFormat.ITALIC));
                var updOrg = new OrganizationForm(console).build();
                var response2=(UpdateResponse)client.sendAndReceiveCommand(new UpdateRequest(id,updOrg,SessionHandler.getInstance().getCurrentUser()));
                if (response.getStatus().equals(ResponseStatus.ERROR)) throw new ResponseErrorMessageException(response.getErrorMessage());
                console.println(ConsoleFormat.coloring(response2.getMessage(), ConsoleFormat.GREEN, ConsoleFormat.BOLD, ConsoleFormat.ITALIC));
            }
        }catch (InvalidFormException e){
            console.printErr("Поля объекта невалидны,объект не создан!");
        }catch (NumberFormatException e){
            console.printErr("Id должно быть числом типа int!");
        }catch (InFileModeException e){
            console.printErr("Произошла ошибка в скриптовом режиме");
        }catch (ResponseErrorMessageException|ErrorResponseException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }

    }
}