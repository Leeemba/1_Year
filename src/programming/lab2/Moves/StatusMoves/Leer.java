package Moves.StatusMoves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class Leer extends StatusMove {
    public Leer(Type type,double pow,double acc){
        super(type,pow,acc);
    }

    @Override
    protected void	applyOppEffects(Pokemon p){
        p.setMod(Stat.DEFENSE,-1);
    }

    @Override
    protected String describe(){
        return "Применяет Leer";
    }
}
