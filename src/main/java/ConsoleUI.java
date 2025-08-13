import java.util.List;

public class ConsoleUI {

    // public static void renderMap(GameMap map, List<Unit> units) {
    //     System.out.println("\n=== Game Map ===");
    //     for (int y = 0; y < map.getHeight(); y++) {
    //         for (int x = 0; x < map.getWidth(); x++) {
    //             Position pos = new Position(x, y);
    //             Unit unitAtPos = findUnitAt(units, pos);
    //             if (unitAtPos != null) {
    //                 if (unitAtPos instanceof BossEnemy) {
    //                     System.out.print("🤖");
    //                 } else if (unitAtPos instanceof Hero) {
    //                     System.out.print("🥷");
    //                 } else {
    //                     System.out.print("👾");
    //                 }
    //             } else {
    //                 // Print the actual tile symbol from your EnvironmentTile
    //                 System.out.print(map.getTile(x, y).getSymbol());
    //             }
    //         }
    //         System.out.println();
    //     }
    // }
    public static void renderMap(GameMap map, List<Unit> units) {
    System.out.println("\n=== Game Map ===");
    EnvironmentTile[][] tiles = map.getTiles();

    for (int y = 0; y < tiles.length; y++) {
        for (int x = 0; x < tiles[0].length; x++) {
            boolean unitFound = false;

            for (Unit unit : units) {
                if (unit.getPosition().getX() == x && unit.getPosition().getY() == y && unit.isAlive()) {
                    if (unit instanceof BossEnemy) {
                        System.out.print("🤖");
                    } else if (unit instanceof Hero) {
                        System.out.print("🥷 ");
                    } else {
                        System.out.print("👾");
                    }
                    unitFound = true;
                    break;
                }
            }

            if (!unitFound) {
                System.out.print(tiles[y][x].getSymbol());  // ✅ y = row, x = column
            }
        }
        System.out.println();
    }
}
public static void renderViewport(GameMap map, List<Unit> units, Position center, int radius) {
    System.out.println("\n=== View ===");

    int minY = Math.max(0, center.getY() - radius);
    int maxY = Math.min(map.getHeight() - 1, center.getY() + radius);
    int minX = Math.max(0, center.getX() - radius);
    int maxX = Math.min(map.getWidth()  - 1, center.getX() + radius);

    for (int y = minY; y <= maxY; y++) {
        for (int x = minX; x <= maxX; x++) {
            Unit unitAt = null;
            for (Unit u : units) {
                if (u.isAlive() && u.getPosition().getX() == x && u.getPosition().getY() == y) {
                    unitAt = u; break;
                }
            }
            if (unitAt != null) {
                if (unitAt instanceof Hero) System.out.print("🥷");
                else if (unitAt instanceof BossEnemy) System.out.print("🤖");
                else System.out.print("👾");
            } else {
                System.out.print(map.getTile(x, y).getSymbol()); // tiles[y][x] inside getTile()
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

