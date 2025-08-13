import java.util.List;

public class Ability {
    private String name;
    private int manaCost;
    private int damage;
    private int range;
    private int radius; // AoE radius

    public Ability(String name, int manaCost, int damage, int range, int radius) {
        this.name = name;
        this.manaCost = manaCost;
        this.damage = damage;
        this.range = range;
        this.radius = radius;
    }

    public String getName() { return name; }
    public int getManaCost() { return manaCost; }
    public int getDamage() { return damage; }
    public int getRange() { return range; }
    public int getRadius() { return radius; }


    @Override
    public String toString() {
        return name + " (Damage: " + damage + ", Cost: " + manaCost + ", Range: " + range + ", Radius: " + radius + ")";
    }
    // Ability.java (add a method)
public void cast(Hero caster, Position target, GameContext ctx) {
    // 1) check cast range from caster to target
    int dx = Math.abs(target.getX() - caster.getPosition().getX());
    int dy = Math.abs(target.getY() - caster.getPosition().getY());
    int dist = Math.max(dx, dy); // Chebyshev
    if (dist > this.range) {
        System.out.println("⚠️ Target out of range.");
        return;
    }

    // 2) get enemies in AoE and damage them
    List<Enemy> victims = ctx.getEnemiesWithin(target, this.radius);
    if (victims.isEmpty()) {
        System.out.println("No enemies in area.");
        return;
    }
    for (Enemy e : victims) {
        e.takeDamage(this.damage);
        System.out.println(e.getName() + " took " + this.damage + " damage at " + e.getPosition());
    }
}

}

