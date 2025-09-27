package Pokemons;

import Moves.StatusMoves.StunSpore;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Weepinbell extends Bellsprout {
    public Weepinbell(String name,int level){
        super(name,level);
        this.setType(Type.POISON,Type.GRASS);
        this.setStats(65,90,50,85,45,55);
        this.addMove(new StunSpore(Type.GRASS,0,75));
    }
}
