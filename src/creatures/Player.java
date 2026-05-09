package creatures;

import abilities.Ability;
import entity.Dice;
import items.Item;
import items.Weapon;

import java.util.ArrayList;
import java.util.Map;

public class Player extends Creature {

    private ArrayList<Item> items;
    private Weapon weapon;
    private Map<Dice.DieType, Integer> statChanges;

    // Basically a test character
    public Player () {
        super();
        setName("Ian");
        items = new ArrayList<>();
        weapon = new Weapon(); // Unarmed

        getAbilities().add(new Ability(
                "Big attack",
                "Take some extra time to hit harder.",
                3,
                Map.of(Dice.DieType.ATTACK, 5)
                )
        );

        getAbilities().add(new Ability(
                "Defend",
                "Protect yourself",
                0,
                Map.of(Dice.DieType.DEFENSE, 3)
                )
        );

        getAbilities().add(new Ability(
                "Test",
                "Add 1 attack. 2 Defend. 3 Focus. 4 Speed.",
                0,
                Map.of(Dice.DieType.ATTACK, 1,
                        Dice.DieType.DEFENSE,2,
                        Dice.DieType.FOCUS, 3,
                        Dice.DieType.SPEED, 4)
        ));

    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
