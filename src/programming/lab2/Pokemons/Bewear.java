package Pokemons;

import Moves.PhysicalMoves.DragonClaw;
import ru.ifmo.se.pokemon.Type;

public class Bewear extends Stufful{

    public Bewear(String name, int level) {
        super(name, level);
        this.setType(Type.NORMAL,Type.FIGHTING);
        this.setStats(120,125,80,55,60,60);
        this.addMove(new DragonClaw(Type.DRAGON,80,100));
    }
}
