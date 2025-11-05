package programming.Places;

public abstract class Place {


    private  String name;



     public Place(String name){
         this.name = name;
     }

    public abstract void showAllSth();


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }




}
