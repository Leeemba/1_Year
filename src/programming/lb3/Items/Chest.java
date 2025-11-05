package programming.lb3.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chest extends Item  {

    private  final List<Item> innerStorage= new ArrayList<>();


    public Chest(String name,Item[] itemsInStorage) {
        super(name);
        innerStorage.addAll(Arrays.asList(itemsInStorage));

    }

    private String techGetName(){
        return super.toString();
    }

    public class Storage {
        public void showAllItem(){
            StringBuilder sb = new StringBuilder();
            if(!(innerStorage.isEmpty())) {
                sb.append("В ").append(techGetName()).append(" находятся: ");
                for (Item item : innerStorage) {
                    sb.append(item).append(" ");
                }
            }else {
                sb.append("В сундуке ничего нет");
            }
            System.out.print(sb);
        }


    }








}





