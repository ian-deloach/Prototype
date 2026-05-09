package abilities;

import creatures.Creature;

public interface AbilityStep {
    public void apply(Creature player, Creature enemy);
}
