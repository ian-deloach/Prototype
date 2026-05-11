package abilities;

import creatures.Creature;
import entity.Dice;

import java.util.Map;

// Stat changes that happen during a round.
public class StatChangePhase implements AbilityPhase {

    private Map<Dice.DieType, Integer> changes;

    public StatChangePhase(Map<Dice.DieType, Integer> changes) {
        this.changes = changes;
    }

    @Override
    public void apply(Creature user, Creature target) {
        Dice.DieType currentStat;
        for (Map.Entry<Dice.DieType, Integer> stat : changes.entrySet()) {
            currentStat = stat.getKey();
            user.getStats().put(currentStat,
                    target.getStats().get(currentStat) + stat.getValue());
        }
    }
}
