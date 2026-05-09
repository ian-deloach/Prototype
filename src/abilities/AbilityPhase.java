package abilities;

import creatures.Creature;

public interface AbilityPhase {
    public void apply(Creature player, Creature enemy);
}
