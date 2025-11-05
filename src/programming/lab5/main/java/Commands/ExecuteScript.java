package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;
import Exceptions.*;
import Managers.CommandManager;
import Managers.RuntimeManager;


public class ExecuteScript extends Command{
    private final Console console;
    private final CommandManager commandManager;
    private final RuntimeManager runtimeManager;
    private final static String NAME = "execute_script";
    private final static String DESCRIPTION = "считывает и исполняет скрипт из указанного файла";

    public ExecuteScript(Console console,CommandManager commandManager,RuntimeManager runtimeManager) {
        super(NAME,DESCRIPTION);
        this.commandManager=commandManager;
        this.console=console;
        this.runtimeManager = runtimeManager;

    }

    @Override
    public void execute(String args)  throws WrongAmountOfArgsException,ExitPoint{
        //ScriptMode scriptMode = new ScriptMode();
        if(args.isBlank()) throw new WrongAmountOfArgsException();
        console.println(ConsoleFormat.coloring("Путь получен успешно!",ConsoleFormat.GREEN,ConsoleFormat.ITALIC));
        runtimeManager.scriptMode(args);

        /*try {
            Console.setFileMode(true);
            scriptMode.addScript(args);

            for (String line = scriptMode.readLine();line !=null;line = scriptMode.readLine()){
                try {
                    String[] userCommand = (line.trim().toLowerCase(Locale.ROOT)+" ").split(" ",2);

                    if(userCommand[0]== null) return;
                    userCommand[1] = userCommand[1].trim();

                    if(userCommand[0].equals("execute_script")&&scriptMode.fileRepeat(userCommand[1])){
                        scriptMode.remove();
                        console.printErr("Найдена рекурсия по пути выполнения скрипта: "+userCommand[0]+" "+userCommand[1]);
                        throw new ScriptRecursionException();
                    }
                    console.println(ConsoleFormat.coloring("Выполнение команды: "+userCommand[0],ConsoleFormat.CYAN,ConsoleFormat.BOLD));
                    commandManager.doing(userCommand[0],userCommand[1]);
                    commandManager.addToHistory(line);
                }catch (ScriptRecursionException e) {
                    throw new ExitPoint();
                }catch (IncorrectCommandException e){
                    console.printErr("Такой команды не существует или неправильно введено имя существующей команды");
                }catch (EmptyCollectionException e) {
                    console.printErr("Невозможно выполнить действие.Коллекция пуста!");
                }
            }scriptMode.remove();

        }catch (FileNotFoundException e){
            console.printErr("Файл не найден!");
        } catch (IOException e) {
            console.printErr("Ошибка ввода/вывода!");
        }

        Console.setFileMode(false);*/
    }
}
