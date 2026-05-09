package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Creature {

    String name;
    int health;
    HashMap<Dice.DieType, Integer> stats = new HashMap<>();
    HashMap<Dice.DieType, Integer> tempStatChanges = new HashMap<>();
    ArrayList<Dice> selectedDice = new ArrayList<>();
    ArrayList<Ability> abilities = new ArrayList<>();

    public Creature() {
        name = "Unknown";
        health = 3;
        stats.put(Dice.DieType.ATTACK, 0);
        stats.put(Dice.DieType.DEFENSE, 0);
        stats.put(Dice.DieType.FOCUS, 0);
        stats.put(Dice.DieType.SPEED, 0);
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
