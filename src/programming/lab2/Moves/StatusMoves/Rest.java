package Moves.StatusMoves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {
    public Rest(Type type,double pow,double acc){
        super(type,pow,acc);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        Effect eff1 = new Effect().condition(Status.SLEEP).turns(2);
        p.addEffect(eff1);
        p.restore();
    }
    @Override
    protected String describe(){
        return "применяет Rest";
    }


}
