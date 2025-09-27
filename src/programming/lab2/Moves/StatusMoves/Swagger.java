package Moves.StatusMoves;

import ru.ifmo.se.pokemon.*;

public class Swagger extends StatusMove {
    public Swagger(Type type, double pow, double acc){
        super(type,pow,acc);
    }


    @Override
    protected void	applyOppEffects(Pokemon p){
        p.confuse();
        p.setMod(Stat.ATTACK,+2);
    }
}
