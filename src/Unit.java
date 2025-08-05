public abstract class Unit {
    protected String name;
    protected int health, mana, movementRange;
    protected Position position;

    public Unit(String name, Position pos, int health, int mana, int movementRange) {
        this.name = name;
        this.position = pos;
        this.health = health;
        this.mana = mana;
        this.movementRange = movementRange;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int dmg) {
        health -= dmg;
        System.out.println(name + " took " + dmg + " damage. Remaining HP: " + health);
    }

    public boolean moveTo(Position newPos, GameMap map) throws InvalidMoveException {
        int dist = position.distanceTo(newPos);
        if (dist > movementRange) throw new InvalidMoveException("Destination too far.");
        if (map.getTileAt(newPos).isBlocked()) throw new InvalidMoveException("Tile is blocked.");
        position = newPos;
        return true;
    }

    public Position getPosition() { return position; }

    public String getName() { return name; }

    public int getHealth() { return health; }

    public int getMana() { return mana; }

    public int getMovementRange() { return movementRange; }

    public abstract void takeTurn(GameContext context);
}
