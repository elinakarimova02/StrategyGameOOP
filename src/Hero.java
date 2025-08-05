import java.util.ArrayList;
import java.util.List;

public class Hero extends Unit {
    private List<Ability> abilities = new ArrayList<>();

    public Hero(String name, Position pos, int health, int mana, int movementRange) {
        super(name, pos, health, mana, movementRange);
    }
    public void useAbility(String abilityName, Position target, GameContext context) {
    Ability selected = null;

    for (Ability a : abilities) {
        if (a.getName().equalsIgnoreCase(abilityName)) {
            selected = a;
            break;
        }
    }

    if (selected == null) {
        System.out.println("Ability not found.");
        return;
    }

    if (mana < selected.getManaCost()) {
        System.out.println("Not enough mana!");
        return;
    }

    List<Unit> allUnits = context.getUnits(); // assuming this gives all units (hero + enemies)

    for (Unit unit : allUnits) {
        if (unit == this || !unit.isAlive()) continue;
        int dist = unit.getPosition().distanceTo(target);
        if (dist <= selected.getRadius()) {
            unit.takeDamage(selected.getDamage());
        }
    }

    mana -= selected.getManaCost();
    System.out.println(name + " used " + selected.getName() + " at " + target + "!");
}

    public void addAbility(Ability a) {
        abilities.add(a);
    }

    public List<Ability> getAbilities() {
        return abilities;
    }


    @Override
    public void takeTurn(GameContext context) {
    }
}

