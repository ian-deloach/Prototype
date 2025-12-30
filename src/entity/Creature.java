package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Creature {

    String name;
    int health;
    HashMap<String, Integer> stats = new HashMap<>();
    HashMap<String, Integer> tempStatChanges = new HashMap<>();
    ArrayList<Dice> selectedDice = new ArrayList<>();
    ArrayList<Ability> abilities = new ArrayList<>();

    public Creature() {
        name = "Unknown";
        health = 3;
        stats.put("attack", 0);
        stats.put("defense", 0);
        stats.put("accuracy", 0);
        stats.put("speed", 0);
        // Each creature starts with a basic attack
        abilities.add(new Ability());
    }

    public class Ability {
        String name;
        String effect;
        int baseDamage;
        int cost;
        boolean isOffense;

        public Ability() {
            name = "Attack";
            effect = "A basic attack.";
            cost = 1;
            isOffense = true;
        }

        public Ability(String name, String effect, int baseDamage, int cost, boolean isOffense) {
            this.name = name;
            this.effect = effect;
            this.baseDamage = baseDamage;
            this.cost = cost;
            this.isOffense = isOffense;
        }
    }

}
