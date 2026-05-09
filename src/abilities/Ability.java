package abilities;

import creatures.Creature;
import entity.Dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ability {
    private String name;
    private String description;
    private int cost;
    private Map<Dice.DieType, Integer> statChanges;
    private List<AbilityStep> steps;
    protected Creature user;
    protected Creature target;

    public Ability() {
        name = "Basic attack";
        description = "Hit the opponent.";
        cost = 0;
    }

    public Ability(String name, String description, int cost, List<AbilityStep> steps) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.steps = steps;
    }

    public void useAbility(Creature user, Creature target) {
        for (AbilityStep step : steps) {
            step.apply(user, target);
        }
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