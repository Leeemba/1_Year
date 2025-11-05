package programming.Enums;

public enum Valuable {
    BILLION("миллиард"),
    MILLION("миллион"),
    HOFTHDS("сотен тысяч"),
    DOFTHSD("десяток тысяч"),
    THSD("тысяча"),
    HUNDREDS("сотня"),
    DOZEN("десяток");





    private  String name;
    Valuable(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
