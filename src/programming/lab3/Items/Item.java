package programming.lb3.Items;

import programming.lb3.Enums.Numbers;
import programming.lb3.Interfaces.*;


import java.util.*;


public class Item  implements AbleToDescribing {


    private final String name;
    private Numbers numbers;

    private final List<String> listOfSpecs = new ArrayList<>();

    public Item(String name){
        this.name = name;

    }


  /*  public  class Goal{

        private final String goal;

        public Goal(String goal) {
            this.goal = goal;
        }


        public String getGoal(){
            return goal;
        }

        @Override
        public String toString(){
            return getGoal();
        }
    }
*/


    public String getName() {
        return name;
    }
    @Override
    public void setSpecs(String[] spec){
        listOfSpecs.addAll(List.of(spec));
    }

    @Override
    public String describing() {
        StringBuilder sb = new StringBuilder();
        for (String spec: listOfSpecs) {
            sb.append(spec).append(" ,");
        }
        return sb.toString();
    }

    @Override
    public String toString(){
        return  describing() +  name.toLowerCase() + " " ;
    }




}
