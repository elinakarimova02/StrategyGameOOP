public class Enemy extends Unit {
    public Enemy(String name, Position pos, int health, int mana, int range) {
        super(name, pos, health, mana, range);
    }

    @Override
    public void takeTurn(GameContext context) {
        // Basic AI: find nearest hero and attack if in range
        for (Unit u : context.getUnits()) {
            if (u instanceof Hero && u.isAlive() && this.position.distanceTo(u.getPosition()) <= 1) {
                u.takeDamage(10);
                System.out.println(name + " attacked " + u.getName());
                return;
            }
        }
        // Otherwise, idle
        System.out.println(name + " is waiting.");
    }
}
