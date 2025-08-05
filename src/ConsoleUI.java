import java.util.List;

public class ConsoleUI {

    public static void renderMap(GameMap map, List<Unit> units) {
        System.out.println("\n=== Game Map ===");
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Position pos = new Position(x, y);
                Unit unitAtPos = findUnitAt(units, pos);
                if (unitAtPos != null) {
                    if (unitAtPos instanceof BossEnemy) {
                        System.out.print(" B "); 
                    } else if (unitAtPos instanceof Hero) {
                        System.out.print(" H "); 
                    } else {
                        System.out.print(" E "); 
                    }
                } else if (map.getTile(x, y).isObstacle()) {
                    System.out.print(" # "); // Blocked terrain
                } else {
                    System.out.print(" . "); // Free tile
                }
            }
            System.out.println();
        }
    }

    private static Unit findUnitAt(List<Unit> units, Position pos) {
        for (Unit unit : units) {
            if (unit.getPosition().equals(pos)) {
                return unit;
            }
        }
        return null;
    }

    public static void displayStatus(Unit unit) {
        System.out.println(unit.getName() + " | HP: " + unit.getHealth() + " | Pos: " + unit.getPosition());
    }
}

