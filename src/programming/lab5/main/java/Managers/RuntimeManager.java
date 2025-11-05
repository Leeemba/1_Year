package Managers;

import ConsoleOperations.*;
import Exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RuntimeManager {
    private final Printable console;
    private final CommandManager commandManager;
    private final List<String> scriptStack=new ArrayList<>();

    public RuntimeManager(Console console,CommandManager commandManager){
        this.console = console;
        this.commandManager = commandManager;
    }



    public void interactiveMode(){
        String[] userCommand;
        Scanner userInput = ScannerManager.getScannerManager().getUserScanner();

        console.printHint(Hint.HELPHINT.toString());
        console.print(ConsoleFormat.coloring("Добро пожаловать! ",ConsoleFormat.WHITE,ConsoleFormat.BOLD));
        while (true){
            console.print(ConsoleFormat.coloring("Введите команду: ",ConsoleFormat.WHITE,ConsoleFormat.BOLD));
            try {
                userCommand = (userInput.nextLine().trim().toLowerCase(Locale.ROOT)+" ").split(" ",2);
                userCommand[1] = userCommand[1].trim();
                run(userCommand);
                commandManager.addToHistory(userCommand[0]);
            }catch (IllegalArgumentException e){
                console.printErr(("Переданы неправильный аргументы"));
            }catch (NoSuchElementException e ){
                console.printErr("Ошибочка! Не ломайте лабу.На неё и так подорожник положили ");
                return;
            }catch (EmptyCollectionException e){
                console.printErr("Невозможно выполнить действие.Коллекция пуста!");
            }catch (IncorrectCommandException e){
                console.printErr("Такой команды не существует или неправильно введено имя существующей команды");
                console.printHint(Hint.HELPHINT.toString());
            }catch (WrongAmountOfArgsException e){
                console.printErr("Введено неверное количество аргументов команды");
            }catch (ExitPoint e){
                console.println(ConsoleFormat.coloring("До встречи!",ConsoleFormat.GREEN,ConsoleFormat.ITALIC,ConsoleFormat.BOLD));
                return;
            }
        }

    }



    public void scriptMode(String args){
        String[] userCommand;
        scriptStack.add(args);
        try (Scanner scriptScanner = new Scanner(new File(args))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner currentScanner = ScannerManager.getScannerManager().getUserScanner();
            ScannerManager.getScannerManager().setUserScanner(scriptScanner);
            Console.setFileMode(true);
            do {
                userCommand = (scriptScanner.nextLine().trim().toLowerCase(Locale.ROOT) + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()){
                    userCommand = (scriptScanner.nextLine().trim().toLowerCase(Locale.ROOT) + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                console.println(ConsoleFormat.coloring("Выполнение команды: "+userCommand[0],ConsoleFormat.CYAN,ConsoleFormat.BOLD));

                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].contains(script)) throw new ScriptRecursionException();
                    }
                }

                run(userCommand);
                commandManager.addToHistory(userCommand[0]);
            } while (scriptScanner.hasNextLine());


            ScannerManager.getScannerManager().setUserScanner(currentScanner);
            Console.setFileMode(false);

        }catch (FileNotFoundException e) {
            console.printErr("Файл не найден!");
        }catch (NoSuchElementException e){
            console.printErr("Файл со скриптом пуст");
        }catch (ScriptRecursionException e) {
            console.printErr("Найдена рекурсия по пути выполнения скрипта");
        }catch (EmptyCollectionException e){
            console.printErr("Невозможно выполнить действие.Коллекция пуста!");
        }catch (IncorrectCommandException e){
            console.printErr("Такой команды не существует или неправильно введено имя существующей команды");
            console.printHint(Hint.HELPHINT.toString());
        }catch (WrongAmountOfArgsException e){
            console.printErr("Введено неверное количество аргументов команды");
        }catch (ExitPoint e){
            console.println(ConsoleFormat.coloring("До встречи!",ConsoleFormat.GREEN,ConsoleFormat.ITALIC,ConsoleFormat.BOLD));
            return;
        }finally {
            scriptStack.remove(scriptStack.size() - 1);
        }


    }


        public void run(String[] userCommand) throws EmptyCollectionException , IncorrectCommandException, WrongAmountOfArgsException,ExitPoint {
        commandManager.doing(userCommand[0],userCommand[1]);
    }




}
