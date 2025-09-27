package Pokemons;

import Moves.PhysicalMoves.Tackle;
import Moves.StatusMoves.Leer;
import Moves.StatusMoves.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Stufful extends Pokemon {
    public Stufful(String name,int level){
        super(name,level);
        this.setType(Type.NORMAL,Type.FIGHTING);
        this.setStats(70,75,50,45,50,50);
        this.addMove(new Leer(Type.NORMAL,0,100));
        this.addMove(new Tackle(Type.NORMAL,40,100));
        this.addMove(new Swagger(Type.NORMAL,0,85));
    }
}
