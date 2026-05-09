package creatures;

import abilities.*;
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
        // Always start with basic attack and defend
        getAbilities().add(AbilityLibrary.defend());
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
