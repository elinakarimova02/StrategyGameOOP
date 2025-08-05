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
}

