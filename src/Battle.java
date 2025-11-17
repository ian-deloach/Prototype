import java.util.ArrayList;
import java.util.Random;

public class Battle {

    Player player;
    Enemy enemy;
    Creature[] fighters = new Creature[2];
    ArrayList<Dice> draftDice = new ArrayList<>();
    Random random = new Random();

    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        fighters[0] = player;
        fighters[1] = enemy;
    }

    // Resets the available dice at the beginning of the round
    public void startRound() {
        draftDice.removeAll(draftDice);
        for (int i = 0; i < 6; i++) {
            draftDice.add(new Dice(
                    random.nextInt(4),
                    random.nextInt(6) + 1
            ));
        }

        draftPeriod();
    }

    // Where the player and enemy pick dice
    public void draftPeriod() {
        // TODO TEST VALUES. MAKE IT SO YOU CAN ACTUALLY PICK DICE
        player.selectedDice.add(draftDice.get(0));
        draftDice.remove(0);

        if (draftDice.isEmpty()) {attack();}

        // TODO implement enemy attack logic
        enemy.selectedDice.add(draftDice.get(0));

        if (draftDice.isEmpty()) {attack();}
        else {draftPeriod();}

    }

    public void alterStats(boolean isAttacking) {
        int statChange;
        for (Creature fighter : fighters) {
            System.out.println(fighter.name);
            for (Dice die : fighter.selectedDice) {
                statChange = isAttacking ? die.value : -die.value;
                // Dice change the stats of the fighter themselves, then change back after attacking.
                fighter.stats.put(die.type,
                        fighter.stats.get(die.type) + statChange);
            }
        }
    }

    // The round ends, and damage is dealt
    // TODO remember to clear selectedDice
    public void attack() {
        alterStats(true);
        if (player.stats.get("attack") > enemy.stats.get("defense")) {
            enemy.health -= 1;
        }

        if (enemy.stats.get("attack") > player.stats.get("defense")) {
            player.health -= 1;
        }

        while (player.health > 0 && enemy.health > 0) {
            startRound();
        }

        if (player.health == 0) {
            System.out.println("Player died.");
        } else {
            System.out.println("Enemy died.");
        }
    }

}
