package creatures;

import abilities.Ability;
import entity.Dice;

import java.util.ArrayList;
import java.util.HashMap;

public class Creature {

    private String name;
    private int health;
    private HashMap<Dice.DieType, Integer> baseStats = new HashMap<>();
    private HashMap<Dice.DieType, Integer> stats = new HashMap<>();
    private HashMap<Dice.DieType, Integer> preparedStatChanges = new HashMap<>();
    private ArrayList<Dice> selectedDice = new ArrayList<>();
    private ArrayList<Ability> abilities = new ArrayList<>();

    public Creature() {
        name = "Unknown";
        health = 3;
        baseStats.put(Dice.DieType.ATTACK, 0);
        baseStats.put(Dice.DieType.DEFENSE, 0);
        baseStats.put(Dice.DieType.FOCUS, 0);
        baseStats.put(Dice.DieType.SPEED, 0);
        // Each creature starts with a basic attack
        abilities.add(new Ability());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public HashMap<Dice.DieType, Integer> getBaseStats() {
        return baseStats;
    }

    public void setBaseStats(HashMap<Dice.DieType, Integer> baseStats) {
        this.baseStats = baseStats;
    }

    public HashMap<Dice.DieType, Integer> getStats() {
        return stats;
    }

    public void setStats(HashMap<Dice.DieType, Integer> stats) {
        this.stats = stats;
    }

    public HashMap<Dice.DieType, Integer> getPreparedStatChanges() {
        return preparedStatChanges;
    }

    public void setPreparedStatChanges(HashMap<Dice.DieType, Integer> preparedStatChanges) {
        this.preparedStatChanges = preparedStatChanges;
    }

    public ArrayList<Dice> getSelectedDice() {
        return selectedDice;
    }

    public void setSelectedDice(ArrayList<Dice> selectedDice) {
        this.selectedDice = selectedDice;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }
}
