package abilities;

import creatures.Creature;
import entity.Dice;

import java.util.Map;

public class StatChangePhase implements AbilityPhase {

    private Map<Dice.DieType, Integer> changes;

    public StatChangePhase(Map<Dice.DieType, Integer> changes) {
        this.changes = changes;
    }

    @Override
    public void apply(Creature player, Creature enemy) {
        Dice.DieType currentStat;
        for (Map.Entry<Dice.DieType, Integer> stat : changes.entrySet()) {
            currentStat = stat.getKey();
            player.getStats().put(currentStat,
                    player.getStats().get(currentStat) + stat.getValue());
        }
    }
}
