public class EnvironmentTile {
    private String type; // e.g., "Grass", "Mountain", "River"
    private boolean blocked;

    public EnvironmentTile(String type, boolean blocked) {
        this.type = type;
        this.blocked = blocked;
    }

    public String getType() {
        return type;
    }

    public boolean isBlocked() {
        return blocked;
    }

    // Optional: some UIs use this to decide what to draw
    public char getSymbol() {
        switch (type.toLowerCase()) {
            case "forest": return 'F';
            case "mountain": return 'M';
            case "river": return 'R';
            case "grass": return '.';
            default: return '?';
        }
    }

    public boolean isObstacle() {
        return blocked;
    }
}
