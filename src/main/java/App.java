import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static void tryMoveWithWASD(Hero hero, char key, GameMap map) {
    int dx = 0, dy = 0;
    switch (key) {
        case 'w': dy = -1; break; // up
        case 's': dy =  1; break; // down
        case 'a': dx = -1; break; // left
        case 'd': dx =  1; break; // right
        default:  return;
    }

    Position cur = hero.getPosition();
    Position next = new Position(cur.getX() + dx, cur.getY() + dy);
    try {
        hero.moveTo(next, map);
        System.out.println("Moved to " + hero.getPosition());
    } catch (InvalidMoveException e) {
        System.out.println("⚠️ " + e.getMessage());
    }
}

private static void useIndexedAbilityCentered(Hero hero, int abilityIndex, GameContext context) {
    if (hero.getAbilities().size() <= abilityIndex) {
        System.out.println("No such ability slotted.");
        return;
    }
    Ability ability = hero.getAbilities().get(abilityIndex);
    Position center = hero.getPosition();   // <-- center on hero
    System.out.println("Casting " + ability.getName() + " around " + center);
    hero.useAbility(ability.getName(), center, context);  // distance=0, valid for any range>=0
}

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("game-config.json"));
        JsonNode level = root.get("1");
        String title = level.get("title").asText();
        System.out.println("Level 1 title: " + title);

        List<Enemy> enemies = new ArrayList<>();
        List<Unit> units = new ArrayList<>();
        EnvironmentTile[][] environmentTile = new EnvironmentTile[5][10];
         for(int i=0; i < 5; i++) {
            for(int j=0; j < 10; j++) {
                environmentTile[i][j] = new EnvironmentTile(level.get("landscape").asText(), false);
            }
        }
        
        
        JsonNode bossEnemiesPositions = level.get("bossPosition");
    
        //adding boss enemy
        int bossX= bossEnemiesPositions.get(0).asInt();
        int bossY= bossEnemiesPositions.get(1).asInt();
        BossEnemy boss = new BossEnemy(level.get("boss").asText(), new Position(bossX, bossY), 120, 0, 2);
        environmentTile[bossY][bossX].setBlocked(true);
        // Example: get the title of level 1
        
        
       
        
        JsonNode enemiesPositions = level.get("enemyPositions");
    
        
        for (JsonNode enemyPosition : enemiesPositions) {
            int x = enemyPosition.get(0).asInt();
            int y = enemyPosition.get(1).asInt();
            environmentTile[x][y].setBlocked(true);// Place enemy on the map
            Enemy enemy=new Enemy(level.get("enemy").asText(), new Position(x, y), 60, 0, 1);
            enemies.add(enemy);
            units.add(enemy);
        }

        

        Hero hero = new Hero(level.get("hero").asText(), new Position(0, 0), 100, 50, 2);


        Ability fireball = new Ability("Fireball", 10, 25, 3, 1); // 1-tile AoE
        Ability slash = new Ability("Slash", 0, 15, 1, 0);        // melee

        hero.addAbility(fireball);
        hero.addAbility(slash);

        
        units.add(hero);
        units.add(boss);
        GameMap map = new GameMap(environmentTile);
        GameContext context = new GameContext(map, units);
        Scanner scanner = new Scanner(System.in);
         

        while (hero.isAlive() && !enemies.isEmpty()) {
            ConsoleUI.renderViewport(map, units, hero.getPosition(), level.get("vision").asInt());
            ConsoleUI.displayStatus(hero);
            for (Enemy enemy : enemies) {
                ConsoleUI.displayStatus(enemy);
            }
            ConsoleUI.displayStatus(boss);
            // --- inside main(), replace your whole "=== Hero Turn ===" block with this:
System.out.println("\n=== Hero Turn ===");
System.out.println("Hero position: " + hero.getPosition());
System.out.print("Action [w/a/s/d to move, e=primary, r=secondary]: ");

String line = scanner.nextLine().trim().toLowerCase();
if (line.isEmpty()) continue;
char key = line.charAt(0);

switch (key) {
    case 'w': case 'a': case 's': case 'd':
        tryMoveWithWASD(hero, key, map);
        break;

    case 'e':
        useIndexedAbilityCentered(hero, 0, context); // primary
        break;

    case 'r':
        useIndexedAbilityCentered(hero, 1, context); // secondary
        break;

    default:
        System.out.println("Unknown action. Use w/a/s/d, e, or r.");
        break;
}


            System.out.println("\n=== Enemy Status ===");
            for (Enemy enemy : enemies) {
                 System.out.println(enemy.getName() + " is at " + enemy.getPosition() + " with HP: " + enemy.getHealth());
        
            }
            
}

        System.out.println(hero.isAlive() ? "🏆 You win!" : "💀 You lost!");
    }
}
