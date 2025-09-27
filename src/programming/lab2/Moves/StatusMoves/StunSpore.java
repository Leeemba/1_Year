package Moves.StatusMoves;

import ru.ifmo.se.pokemon.*;

public class StunSpore extends StatusMove {
    public StunSpore(Type type, double pow, double acc){
        super(type,pow,acc);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        Effect effect = new Effect().condition(Status.PARALYZE);
        Effect effect1 = new Effect().chance(0.25).attack(0);
        p.addEffect(effect);
        p.addEffect(effect1);
        p.setMod(Stat.SPEED,-3);
    }

    @Override
    protected String describe(){
        return ("Применяет Stun Spore");
    }
}

