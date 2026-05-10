package abilities;

import entity.Dice;

import java.util.List;
import java.util.Map;

// Holds all abilities in the game and their data.

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
                        new StatChangePhase(
                                Map.of(Dice.DieType.ATTACK, 3)
                        ),
                        new DamagePhase()
                )
        );
    }

    public static Ability defend() {
        return new Ability(
                "Defend",
                "Protect yourself.",
                0,
                List.of(
                        new StatChangePhase(
                                Map.of(Dice.DieType.DEFENSE, 3)
                        )
                )
        );
    }

    public static Ability prepare() {
        return new Ability(
                "Prepare",
                "Set yourself for the next turn. +2 Attack +2 Focus.",
                0,
                List.of(
                        new PreparationPhase(
                                Map.of(
                                        Dice.DieType.ATTACK, 2,
                                        Dice.DieType.FOCUS, 2
                                )
                        )
                )
        );

    }

}
