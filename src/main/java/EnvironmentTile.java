
public class EnvironmentTile {
    private String type; // e.g., "Grass", "Mountain", "River"
    private boolean blocked;

    public EnvironmentTile(String type, boolean blocked) {
        this.type = type;
        this.blocked = blocked;
    }

    public EnvironmentTile() {
        //TODO Auto-generated constructor stub
    }

    public String getType() {
        return type;
    }

    public boolean isBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    // Optional: some UIs use this to decide what to draw
    public String getSymbol() {
        return type;
    }

    public boolean isObstacle() {
        return blocked;
    }
}
