package Enums;

public enum Numbers {
    NULL(""),
    ONE(" Один "),
    TWO(" Два "),
    THREE(" Три "),
    FOUR(" Четыре "),
    FIVE(" Пять "),
    SIX(" Шесть "),
    SEVEN(" Семь "),
    EIGHT(" Восемь "),
    NINE(" Девять ");


    private final String name;
    Numbers(String name){
    this.name = name;
    }
    @Override
    public String toString(){
        return name.toLowerCase();
    }

}
