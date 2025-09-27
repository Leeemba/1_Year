package Moves.PhysicalMoves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class Tackle extends PhysicalMove {
    public Tackle(Type type,double pow,double acc){
        super(type,pow,acc);
    }

    @Override
    protected String describe(){
        return "Применяет Tackle";
    }
}
