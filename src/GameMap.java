public class GameMap {
    private EnvironmentTile[][] tiles;

    public GameMap(EnvironmentTile[][] tiles) {
        this.tiles = tiles;
    }

    public int getHeight() {
        return tiles.length;
    }

    public int getWidth() {
        return tiles[0].length;
    }

    public EnvironmentTile getTile(int x, int y) {
        if (x < 0 || y < 0 || y >= getHeight() || x >= getWidth()) {
            throw new IndexOutOfBoundsException("Position out of map bounds");
        }
        return tiles[y][x]; // Note: tiles[row][col] = tiles[y][x]
    }

    public EnvironmentTile getTileAt(Position pos) {
        return getTile(pos.getX(), pos.getY());
    }
}

