package Pokemons;

import Moves.SpecialMoves.ChargeBeam;
import Moves.SpecialMoves.LeafStorm;
import Moves.SpecialMoves.MagicalLeaf;
import Moves.StatusMoves.Rest;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Celebi extends Pokemon {
    public Celebi(String name,int level){
        super(name,level);
        this.setType(Type.GRASS);
        this.setStats(100,100,100,100,100,100);
        this.addMove(new MagicalLeaf(Type.GRASS,60,100));
        this.addMove(new Rest(Type.PSYCHIC,100,100));
        this.addMove(new ChargeBeam(Type.ELECTRIC,50,90));
        this.addMove(new LeafStorm(Type.GRASS,130,90));

    }







}
