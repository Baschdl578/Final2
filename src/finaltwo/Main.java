package finaltwo;

import java.util.LinkedList;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class Main {
    private static TurningRule rules;
    private static LinkedList<Ant> ants = new LinkedList<Ant>();

    public static TurningRule getTurningRules() {
        return rules;
    }

    public static LinkedList<Ant> getAnts() {
        return ants;
    }

    public static void deleteAnt(Ant ant) {
        ants.remove(ant);
        ant.getPosition().setOccupiedBy(null);
    }
}
