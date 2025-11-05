package Commands;

/**
 * Абстрактный суперкласс для всех команд, управляющих коллекцией
 *
 *
 * @implNote реализует интерфейс {@link Executable}.
 *
 * Метод execute определяет логику для исполнения команд
 *
 * @author Maxim Baukin
 */
public abstract class Command implements Executable{
    private final String name;

    private final String description;

    public Command(String name,String description) {
        this.name = name;
        this.description = description;
        
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString(){
        return name+": "+description;
    }
}
