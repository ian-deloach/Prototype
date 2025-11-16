import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    String name;
    int health;
    HashMap<String, Integer> stats = new HashMap<>();
    ArrayList<Item> items;
    Weapon weapon;

    // Basically a test character
    public Player () {
        name = "Unknown";
        health = 3;
        stats.put("attack", 0);
        stats.put("defense", 0);
        stats.put("accuracy", 0);
        stats.put("speed", 0);
        items = new ArrayList<>();
        weapon = new Weapon(); // Unarmed
    }
    public Player (String name, int health, HashMap<String, Integer> stats) {

    }

}
