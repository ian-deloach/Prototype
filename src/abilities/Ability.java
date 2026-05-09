package abilities;

import entity.Dice;

import java.util.Map;

public class Ability {
    private String name;
    private String description;
    private int cost;
    private boolean isOffense;
    private Map<Dice.DieType, Integer> statChanges;

    public Ability() {
        name = "Attack";
        description = "A basic attack.";
        cost = 0;
        isOffense = true;
    }

    public Ability(String name, String description, int cost, Map<Dice.DieType, Integer> statChanges) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.statChanges = statChanges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public int getCost() {
        return cost;
    }

    public Map<Dice.DieType, Integer> getStatChanges() {
        return statChanges;
    }

}