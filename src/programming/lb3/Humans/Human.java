package programming.lb3.Humans;

import programming.lb3.Enums.Action;
import programming.lb3.Places.Place;

public  abstract class Human  {
    private final String name;
    private Place place;

    public Human(String name){
        this.name = name;

    }

    public String getName(){
        return name;
    }
    public void doTogether(Human[] humans, Action[] actions){

        StringBuilder sb = new StringBuilder(this.getName());
        for (Human human: humans) {
        sb.append(" и ").append(human.getName());
        } sb.append(" вместе совершили действия: ");
        for (Action action:actions) {
            sb.append(action).append(" ");
        }
        System.out.print(sb);
    }

    public void doAction(Action[] actions){
        StringBuilder sb = new StringBuilder(this.getName());
        sb.append(" совершил действия: ");
        for (Action action:actions) {
            sb.append(action).append(" ");
        }
        System.out.print(sb);
    }



    @Override
    public String toString(){
        return getName().toLowerCase();
    }


}
