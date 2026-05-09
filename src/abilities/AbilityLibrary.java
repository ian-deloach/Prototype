package abilities;

import entity.Dice;

import java.util.List;
import java.util.Map;

public class AbilityLibrary extends Ability {

    public static Ability attack() {
        return new Ability();
    }

    public static Ability bigAttack () {
        return new Ability(
                "Big attack",
                "Take some extra time to hit harder.",
                3,
                List.of(
                        new StatChangeStep(
                                Map.of(Dice.DieType.ATTACK, 3)
                        ),
                        new DamageStep()
                )
        );
    }

    public static Ability defend() {
        return new Ability(
                "Defend",
                "Protect yourself.",
                0,
                List.of(
                        new StatChangeStep(
                                Map.of(Dice.DieType.DEFENSE, 3)
                        )
                )
        );
    }

}
