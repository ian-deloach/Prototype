import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Creature {

    ArrayList<Item> items;
    Weapon weapon;

    // Basically a test character
    public Player () {
        super();
        this.name = "ian";
        items = new ArrayList<>();
        weapon = new Weapon(); // Unarmed
    }

}
