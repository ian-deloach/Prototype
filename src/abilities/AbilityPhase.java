package abilities;

import creatures.Creature;

// Phases are essentially what abilities do. Ex. "DamagePhase" means it is an attack.

public interface AbilityPhase {
    public void apply(Creature player, Creature enemy);
}
