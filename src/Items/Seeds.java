package Items;

import Enums.Numbers;

public record Seeds(String name, boolean isDelivered) {

    public String parted(Numbers numbers,boolean isFair){
        StringBuilder sb = new StringBuilder();
        if (isDelivered){
            sb.append(toString()).append(" доставлены").append(" и поделены на ");
            if (isFair){
                sb.append("равных ").append("частей:").append(numbers);
            }else {
                sb.append("неравных ").append("частей:").append(numbers);
            }
        }else{
            sb.append(toString()).append(" не доставлены").append(" и делить нечего");
        }

      return sb.toString();
    }


    @Override
    public String toString(){
        return name;
    }

}
