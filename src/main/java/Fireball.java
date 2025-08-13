public class Fireball extends Ability{

    public Fireball(String name, int manaCost, int damage, int range, int radius) {
        super(name, manaCost, damage, range, radius);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Fireball Ability: " + super.toString();
    }
    
}
