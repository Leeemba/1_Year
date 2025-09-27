package Moves.SpecialMoves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class LeafStorm extends SpecialMove {
    public LeafStorm(Type type,double pow, double acc){
        super(type,pow,acc);
    }
    @Override
    protected boolean checkAccuracy(Pokemon att, Pokemon deff){
        return true;
    }
    @Override
    protected String describe(){
        return ("Применяет Leaf Storm");
    }
}
