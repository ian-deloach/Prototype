package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Creature {

    String name;
    int health;
    HashMap<String, Integer> stats = new HashMap<>();
    ArrayList<Dice> selectedDice = new ArrayList<>();

    public Creature() {
        name = "Unknown";
        health = 3;
        stats.put("attack", 0);
        stats.put("defense", 0);
        stats.put("accuracy", 0);
        stats.put("speed", 0);
    }

}
