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
}
