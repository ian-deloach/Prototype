package entity;

import java.util.ArrayList;

public class Player extends Creature {

    private ArrayList<Item> items;
    private Weapon weapon;

    // Basically a test character
    public Player () {
        super();
        setName("Ian");
        items = new ArrayList<>();
        weapon = new Weapon(); // Unarmed
        getAbilities().add(new Ability(
                "Big attack",
                "Take some extra time to hit harder.",
                5,
                3,
                true
                )
        );

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
