package abilities;

import creatures.Creature;
import entity.Dice;

import java.util.Map;

/* "Prepared" stats can be seen as temporary stat changes that apply during the start
*   of the next round. */
public class PreparationPhase implements AbilityPhase {

    private Map<Dice.DieType, Integer> prepStats;

    public PreparationPhase(Map<Dice.DieType, Integer> prepStats) {
        this.prepStats = prepStats;
    }

    @Override
    public void apply(Creature user, Creature target) {
        Dice.DieType currentStat;
        for (Map.Entry<Dice.DieType, Integer> stat : prepStats.entrySet()) {
            currentStat = stat.getKey();
            user.getPreparedStatChanges().put(currentStat, stat.getValue());
        }
    }
}
