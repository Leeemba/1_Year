package Moves.StatusMoves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class SwordsDance extends StatusMove {
    public SwordsDance(Type type,double pow, double acc){
        super(type,pow,acc);
    }

    @Override
    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.ATTACK,2);
    }

    @Override
    protected String describe(){
        return ("Применяет Swords Dance");
    }
}
