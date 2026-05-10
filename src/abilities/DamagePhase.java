package abilities;

import creatures.Creature;
import entity.Dice;

public class DamagePhase implements AbilityPhase {
    @Override
    public void apply(Creature user, Creature target) {
        if (user.getStats().get(Dice.DieType.ATTACK) >
                target.getStats().get(Dice.DieType.DEFENSE)) {
            System.out.println(target.getName() + " took 1 damage!");
            System.out.println("Defense: " + target.getStats().get(Dice.DieType.DEFENSE)
                    + " Attack: " + user.getStats().get(Dice.DieType.ATTACK));

            target.setHealth(target.getHealth() - 1);

        } else {
            System.out.println(target.getName() + " blocked!");
            System.out.println("Defense: " + target.getStats().get(Dice.DieType.DEFENSE)
                    + " Attack: " + user.getStats().get(Dice.DieType.ATTACK));
        }
    }
}
