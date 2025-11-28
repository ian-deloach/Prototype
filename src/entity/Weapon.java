package entity;

import java.util.HashMap;

public class Weapon extends Item {

    HashMap<String, Integer> stats = new HashMap<>();

    // The default "weapon" is bare fists
    public Weapon() {
        name = "Unarmed";
        description = "Nothing but your fists.";
        stats.put("attack", 0);
        stats.put("defense", 0);
        stats.put("accuracy", 0);
        stats.put("speed", 0);
    }

}
