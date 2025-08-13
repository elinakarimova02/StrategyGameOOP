
import java.util.ArrayList;
import java.util.List;

public class GameContext {
    private final GameMap map;
    private final List<Unit> units;

    public GameContext(GameMap map, List<Unit> units) {
        this.map = map;
        this.units = units;
    }

    public GameMap getMap() {
        return map;
    }

    public List<Unit> getUnits() {
        return units;
    }
    // GameContext.java
public List<Enemy> getEnemiesWithin(Position center, int radius) {
    List<Enemy> hits = new ArrayList<>();
    for (Unit u : units) {
        if (u instanceof Enemy && u.isAlive()) {
            Position p = u.getPosition();
            int dx = Math.abs(p.getX() - center.getX());
            int dy = Math.abs(p.getY() - center.getY());
            int chebyshev = Math.max(dx, dy);    // square AoE
            if (chebyshev <= radius) hits.add((Enemy) u);
        }
    }
    return hits;
}

}
