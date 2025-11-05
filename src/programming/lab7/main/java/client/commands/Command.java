package client.commands;

public abstract class Command implements Executable{
    private final String name;
    private  String description;

    public Command(String name) {
        this.name = name;
    }
    public Command(String name,String description) {
        this.name = name;
        this.description =description;
    }

    public String getName() {
        return name;
    }
    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return name;
    }
}

