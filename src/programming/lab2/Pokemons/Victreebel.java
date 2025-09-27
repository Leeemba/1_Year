package Pokemons;

import Moves.StatusMoves.SleepPowder;
import ru.ifmo.se.pokemon.Type;

public class Victreebel extends  Weepinbell {
    public Victreebel(String name,int level) {
        super(name,level);
        this.setType(Type.GRASS,Type.POISON);
        this.setStats(80,105,65,100,70,70);
        this.addMove(new SleepPowder(Type.GRASS,0,75));
    }
}
