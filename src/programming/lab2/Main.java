import Pokemons.*;
import ru.ifmo.se.pokemon.*;
public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();

        Bewear p1 = new Bewear("Чужой",1);
        Celebi p2 = new Celebi("Хищник", 1);
        Stufful p3 = new Stufful("Капибара",1);

        Bellsprout p4 = new Bellsprout("Глист",1);
        Weepinbell p5 = new Weepinbell("Глист побольше",1);
        Victreebel p6 = new Victreebel("Глист-переросток",1);

        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);

        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);

        b.go();
    }
}
