package Moves.PhysicalMoves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class DragonClaw extends PhysicalMove {
    public DragonClaw(Type type,double pow, double acc){
        super(type,pow,acc);
    }

    @Override
    protected String describe(){
        return "Применяет Dragon Claw";
    }
}

