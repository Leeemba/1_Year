package programming.Places;

import programming.Humans.Human;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Meeting extends Place{
    private final List<Human> humanList = new ArrayList<>();

    public  Meeting(String name, Human[] humans){
        super(name);
        humanList.addAll(Arrays.asList(humans));
    }

    @Override
    public String toString(){
        return getName().toLowerCase() ;
    }

    @Override
    public void showAllSth(){
        System.out.print("На " + toString() + " находятся: ");
        for (Human human:humanList) {
            System.out.print( human.getName() + " ");
        }
        System.out.print(".");

    }
}
