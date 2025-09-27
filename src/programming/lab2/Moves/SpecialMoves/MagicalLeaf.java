package Moves.SpecialMoves;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class MagicalLeaf extends SpecialMove {

    public MagicalLeaf(Type type, double pow, double acc){
        super(type,pow,acc);

    }

   @Override
    protected String describe(){
        return ("Применяет Magical Leaf");
   }



}
