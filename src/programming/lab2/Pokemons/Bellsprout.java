package Pokemons;

import Moves.PhysicalMoves.Facade;
import Moves.StatusMoves.SwordsDance;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Bellsprout extends Pokemon {
    public Bellsprout(String name, int level){
        super(name,level);
        this.setType(Type.GRASS,Type.POISON);
        this.setStats(50,75,35,70,30,40);
        this.addMove(new Facade(Type.NORMAL,70,100));
        this.addMove(new SwordsDance(Type.NORMAL,0,100));
    }
}
