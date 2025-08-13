import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create a basic 3x3 map
        EnvironmentTile[][] tiles = {
            { new EnvironmentTile("Grass", false), new EnvironmentTile("Grass", false), new EnvironmentTile("Forest", false) },
            { new EnvironmentTile("Grass", false), new EnvironmentTile("Mountain", true), new EnvironmentTile("Grass", false) },
            { new EnvironmentTile("River", true), new EnvironmentTile("Grass", false), new EnvironmentTile("Grass", false) }
        };

        GameMap map = new GameMap(tiles);
        List<Unit> units = new ArrayList<>();

        Hero hero = new Hero("Knight", new Position(0, 0), 100, 50, 2);

        Ability fireball = new Ability("Fireball", 10, 25, 3, 1); // 1-tile AoE
        Ability slash = new Ability("Slash", 0, 15, 1, 0);        // melee

        hero.addAbility(fireball);
        hero.addAbility(slash);

        Enemy goblin = new Enemy("Goblin", new Position(2, 2), 60, 0, 1);
        BossEnemy boss = new BossEnemy("Dragon", new Position(2, 0), 120, 0, 2);

        units.add(goblin);
        units.add(boss);

        GameContext context = new GameContext(map, units);
        Scanner scanner = new Scanner(System.in);

        while (hero.isAlive() && goblin.isAlive()) {
            ConsoleUI.renderMap(map, units);
            ConsoleUI.displayStatus(hero);
            ConsoleUI.displayStatus(goblin);

            System.out.println("\n=== Hero Turn ===");
            System.out.println("Hero position: " + hero.getPosition());
            System.out.print("Do you want to (move) or (ability)? ");
            String action = scanner.nextLine();

            if (action.equalsIgnoreCase("move")) {
                System.out.print("Enter new x y: ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                scanner.nextLine();

                try {
                    hero.moveTo(new Position(x, y), map);
                    System.out.println("Moved to " + hero.getPosition());
                } catch (InvalidMoveException e) {
                    System.out.println("⚠️ " + e.getMessage());
                }
            } else if (action.equalsIgnoreCase("ability")) {
                System.out.println("Available Abilities:");
                for (Ability a : hero.getAbilities()) {
                    System.out.println("- " + a.getName());
                }
                System.out.print("Enter ability name: ");
                String abilityName = scanner.next();

                System.out.print("Enter target x y: ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                scanner.nextLine();

                Position target = new Position(x, y);
                hero.useAbility(abilityName, target, context);
            }

            System.out.println("\n=== Enemy Status ===");
            System.out.println(goblin.getName() + " is at " + goblin.getPosition() + " with HP: " + goblin.getHealth());
        }

        System.out.println(hero.isAlive() ? "🏆 You win!" : "💀 You lost!");
    }
}


