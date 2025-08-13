
import java.util.List;

public class BossEnemy extends Enemy {
    private int rageThreshold = 30; // Enters rage mode below this HP
    private boolean enraged = false;

    public BossEnemy(String name, Position pos, int health, int mana, int range) {
        super(name, pos, health, mana, range);
    }

    @Override
    public void takeTurn(GameContext context) {
        if (!isAlive()) return;

        // Check rage mode
        if (!enraged && getHealth() <= rageThreshold) {
            enraged = true;
            System.out.println(name + " has entered RAGE MODE! 🔥");
        }

        // Attack all heroes in range
        List<Unit> units = context.getUnits();
        boolean attacked = false;

        for (Unit u : units) {
            if (u instanceof Hero && u.isAlive()) {
                int dist = getPosition().distanceTo(u.getPosition());

                // If in range, attack
                if (dist <= getMovementRange()) {
                    int damage = enraged ? 20 : 10;
                    u.takeDamage(damage);
                    System.out.println(name + " attacks " + u.getName() + " for " + damage + " damage!");
                    attacked = true;
                }
            }
        }

        if (!attacked) {
            System.out.println(name + " is waiting...");
        }
    }

    @Override
    public String toString() {
        return "BossEnemy{name=" + getName() + ", HP=" + getHealth() + ", Enraged=" + enraged + "}";
    }
}

