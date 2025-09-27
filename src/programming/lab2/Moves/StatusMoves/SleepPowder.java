package Moves.StatusMoves;

import ru.ifmo.se.pokemon.*;

public class SleepPowder extends StatusMove {
    public SleepPowder(Type type,double pow,double acc){
        super(type,pow,acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        Effect eff3 = new Effect().condition(Status.SLEEP).turns((int) Math.random()*(3+1));
        p.addEffect(eff3);
    }

    @Override
    protected String describe(){
        return ("Применяет Sleep Powder");
    }
}
