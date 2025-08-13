import java.util.ArrayList;
import java.util.List;

public class Hero extends Unit {
    private List<Ability> abilities = new ArrayList<>();

    public Hero(String name, Position pos, int health, int mana, int movementRange) {
        super(name, pos, health, mana, movementRange);
    }
    // Hero.java (your existing method can be adjusted like this)
public void useAbility(String abilityName, Position target, GameContext ctx) {
    Ability a = getAbilities().stream()
        .filter(ab -> ab.getName().equalsIgnoreCase(abilityName))
        .findFirst().orElse(null);

    if (a == null) {
        System.out.println("⚠️ Ability not found: " + abilityName);
        return;
    }
    // (optional) mana checks here
    a.cast(this, target, ctx);
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

