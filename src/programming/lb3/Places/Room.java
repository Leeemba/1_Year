package programming.lb3.Places;
import programming.lb3.Interfaces.Comparing;
import programming.lb3.Items.Item;
import programming.lb3.Enums.Numbers;
import java.util.*;


public class Room extends Place implements Comparing {

    private List<Item> listOfItems = new ArrayList<>();
    private final Numbers num;
    private  final int size = (int) (Math.random()*10);



    public Room(String name, Numbers num){
        super(name);
        this.num = num;
        if (name.isBlank()){
            setName("комната");
        }
    }

    public Room(String name, Numbers num,Item[] items){
         super(name);
         this.num = num;
         this.listOfItems = List.of(items);

        if (name.isBlank()){
            super.setName("комната");
        }

     }

     public void addItem(Item item){
       this.listOfItems.add(item);
       System.out.println("В" + toString() +" добавили " + item.getName());
     }

     public void addManyItems(Item[] items){
        this.listOfItems.addAll(Arrays.asList(items));
        StringBuilder sb = new StringBuilder("В ").append(toString()).append(" передвинули: ");
        for (Item item: items){
            sb.append(item.getName()).append(" ");
        }
        sb.append(".");
         System.out.println(sb);

     }


    @Override
     public  void  compare(){

    }
     public String compare(Room roomToCompare){
        StringBuilder sb = new StringBuilder();
        if (this.size<roomToCompare.size){
             sb.append(this.getName()).append(this.num).append(" меньше ").append(roomToCompare.getName()).append(roomToCompare.num).append(". ");
         } else if (this.size == roomToCompare.size) {
             sb.append(this.getName()).append(this.num).append(" равна ").append(roomToCompare.getName()).append(roomToCompare.num).append(". ");
         } else {
             sb.append(this.getName()).append(this.num).append(" больше ").append(roomToCompare.getName()).append(roomToCompare.num).append(". ");
         }
        return(sb.toString());
     }


    @Override
     public void showAllSth(){
        StringBuilder sb = new StringBuilder("В " + this + "находится: ");
        for (Item item:listOfItems) {
             sb.append(item).append(" ");
         }
        System.out.print(sb.toString());
     }



     @Override
    public String toString(){
         return getName().toLowerCase() + num;
     }


}