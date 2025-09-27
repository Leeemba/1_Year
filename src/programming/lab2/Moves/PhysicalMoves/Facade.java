package Moves.PhysicalMoves;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {
    public Facade(Type type,double pow,double acc){
        super(type,pow,acc);
    }

    @Override
    protected void applySelfEffects(Pokemon p){
        if (p.getCondition() == Status.BURN || p.getCondition() == Status.POISON || p.getCondition() == Status.PARALYZE){
            p.setMod(Stat.ATTACK, 3);
        }
    }

    @Override
    protected String describe(){
        return ("Применяет Facade");
    }

}


