import Enums.Action;
import Humans.*;
import Sentence.Sentence;
import Items.*;


import Enums.Numbers;
import Places.*;


public class Main {
    public static void main(String[] args) {

        //Создание технического объекта для определения начала и конца предложения.
        Sentence sentence = new Sentence();

        //Блок создания объектов типа Item
        Share share = new Share("акции общества", "фертинги");
        Wardrobe wardrobe = new Wardrobe("шкаф");
        Chest chest = new Chest("сундук",new Item[]{share});
        Chest.Storage storage = chest.new Storage();
        Seeds seeds = new Seeds("семена гигантских растений", true);


        Miga miga = new Miga("Мига");
        Julio julio = new Julio("Жулио");

        Room room1 = new Room("",Numbers.ONE);
        Room room2 = new Room("",Numbers.TWO,new Item[]{chest,wardrobe});
        Meeting meeting = new Meeting("заседание",new Human[]{julio,miga});


        // Блок кода отвечающий за

        // 1 предложение
        sentence.capitalized(room2.compare(room1));

        // 2 предложение
        chest.setSpecs(new String[]{"большой","несгораемый"});
        wardrobe.setSpecs(new String[]{"большой","несгораемый"});
        room2.showAllSth();
        sentence.dot();

        // 3 предложение
        share.numConstructor(Numbers.NULL,Numbers.FIVE,Numbers.NULL,Numbers.NULL,Numbers.NULL,Numbers.NULL,Numbers.NULL,Numbers.NULL);
        storage.showAllItem();
        sentence.dot();

        // 4 предложение
        meeting.showAllSth();

        // 5 предложение
        miga.doTogether(new Human[]{julio},new Action[]{Action.MAKEMEETING});
        sentence.dot();

        // 6 предложение
        miga.doAction(new Action[]{Action.MAKEOFFER,Action.SELL});
        share.numConstructor(Numbers.NULL,Numbers.TWO,Numbers.NULL,Numbers.NULL,Numbers.NULL,Numbers.NULL,Numbers.NULL,Numbers.NULL);
        System.out.print(share);
        sentence.dot();

        // 7 предложение
        miga.doTogether(new Human[]{julio},new Action[]{Action.SEPARATE});
        share.numConstructor(Numbers.NULL,Numbers.THREE,Numbers.NULL,Numbers.NULL,Numbers.NULL,Numbers.NULL,Numbers.NULL,Numbers.NULL);
        System.out.print(share);
        sentence.dot();

        //8 предложение
        sentence.capitalized(seeds.parted(Numbers.FIVE,true));

    }
}





