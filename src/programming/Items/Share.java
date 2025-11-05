package programming.Items;

import programming.Enums.Numbers;
import programming.Enums.Valuable;

public class Share extends Item {

    private String value;
    private final String currency;

    public Share(String name,String currency)  {
        super(name);
        if (currency.isBlank()){
            throw new IllegalArgumentException("Эта строка не должна быть пустой");
        }
        this.currency = currency;

    }


    public void numConstructor(Numbers num8,Numbers num7,Numbers num6,Numbers num5,Numbers num4,Numbers num3,Numbers num2,Numbers num1){
        String val8 = num8.toString() + Valuable.BILLION.toString();
        String val7 = num7.toString() + Valuable.MILLION.toString();
        String val6 = num6.toString() + Valuable.HOFTHDS.toString();
        String val5 = num5.toString() + Valuable.DOFTHSD.toString();
        String val4 = num4.toString() + Valuable.THSD.toString();
        String val3 = num3.toString() + Valuable.HUNDREDS.toString();
        String val2 = num2.toString() + Valuable.DOZEN.toString();
        String val1 = num1.toString();

        switch (num8){
            case NULL -> val8 = "";
            case ONE  -> val8 = num8.toString() + " миллиард ";
            case NINE,EIGHT,SEVEN,SIX,FIVE ->  val8 = num8.toString() + " миллиардов ";
            case FOUR,THREE,TWO -> val8 = num8.toString()+" миллиарда ";
       }
        switch (num7){
            case NULL -> val7 = "";
            case ONE  -> val7 = num7.toString() + " миллион ";
            case NINE,EIGHT,SEVEN,SIX,FIVE ->  val7 = num7.toString() + " миллионов ";
            case FOUR,THREE,TWO -> val7 = num7.toString()+" миллиона ";
        }
        switch (num6){
            case NULL -> val6 = "";
            case ONE  -> val6 = " одна " + "сотня тысяч";
            case NINE,EIGHT,SEVEN,SIX,FIVE ->  val6 = num6.toString() + " сотен тысяч ";
            case FOUR,THREE,TWO -> val6 = num6.toString()+" сотни тысяч ";
        }
        switch (num5){
            case NULL -> val5 = "";
            case ONE  -> val5 = num5.toString() + "десяток тысяч";
            case NINE,EIGHT,SEVEN,SIX,FIVE ->  val5 = num5.toString() + "десятков тысяч";
            case FOUR,THREE,TWO -> val5 = num5.toString()+" десятка тысяч ";
        }
        switch (num4){
            case NULL -> val4 = "";
            case ONE  -> val4 = " одна " + " тысяча ";
            case NINE,EIGHT,SEVEN,SIX,FIVE ->  val4 = num4.toString() + " тысяч ";
            case FOUR,THREE,TWO -> val4 = num4.toString()+ " тысячи ";
        }
        switch (num3){
            case NULL -> val3 = "";
            case ONE  -> val3 = " одна " + " сотня ";
            case NINE,EIGHT,SEVEN,SIX,FIVE ->  val3 = num3.toString() + " сотен ";
            case FOUR,THREE,TWO -> val3 = num3.toString()+" сотни ";
        }
        switch (num2){
            case NULL -> val2 = "";
            case ONE  -> val2 = num2.toString() + " десяток ";
            case NINE,EIGHT,SEVEN,SIX,FIVE ->  val2 = num2.toString() + " десятков ";
            case FOUR,THREE,TWO -> val2 = num2.toString()+ " десятка ";
        }
        switch (num1){
            case NULL -> val1 = "";
            case ONE, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO -> val1 = num1.toString();
        }
        StringBuilder sb = new StringBuilder();

        sb.append(val8).append(val7).append(val6).append(val5).append(val4).append(val3).append(val2).append(val1);
        this.value = sb.toString();
    }

    public String getValue(){
        return  value;
    }
    public String getCurrency(){
        return  currency;
    }




    @Override
    public String toString(){
        if (getValue().isBlank()){
            return getName();
        }else if(getCurrency().isBlank()) {
            return getName() + " на сумму: " + getValue();
        }else {
            return getName() + " на сумму: " + getValue() + " в валюте: " + getCurrency();

        }
    }

}
