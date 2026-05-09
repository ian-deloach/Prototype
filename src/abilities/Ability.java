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
    private List<AbilityPhase> phases;
    protected Creature user;
    protected Creature target;

    public Ability() {
        name = "Basic attack";
        description = "Hit the opponent.";
        cost = 0;
        phases = List.of(
                new DamagePhase()
        );
    }

    public Ability(String name, String description, int cost, List<AbilityPhase> phases) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.phases = phases;
    }

    public void useAbility(Creature user, Creature target) {
        for (AbilityPhase phase : phases) {
            phase.apply(user, target);
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

}