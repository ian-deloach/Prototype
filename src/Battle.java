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

    public void runBattle() {

        while (player.health > 0 && enemy.health > 0) {
            startRound();
        }

        if (player.health <= 0) {
            System.out.println("Player died.");
        } else {
            System.out.println("Enemy died.");
        }
    }

    // Resets the available dice at the beginning of the round
    public void startRound() {
        draftDice.clear();
        for (int i = 0; i < 6; i++) {
            draftDice.add(new Dice(
                    random.nextInt(4),
                    random.nextInt(6) + 1
            ));
        }

        System.out.println("Draft size: " + draftDice.size());
        draftPeriod();
    }

    // Where the player and enemy pick dice
    public void draftPeriod() {

        while (!draftDice.isEmpty()) {
            // TODO TEST VALUES. MAKE IT SO YOU CAN ACTUALLY PICK DICE
            player.selectedDice.add(draftDice.remove(0));
            // TODO implement enemy attack logic
            enemy.selectedDice.add(draftDice.remove(0));
            System.out.println("Remaining dice: " + draftDice.size());
        }

        attack();

    }

    public void alterStats(boolean isAttacking) {
        int statChange;
        for (Creature fighter : fighters) {
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
            System.out.println("Enemy hit! Lives left: " + enemy.health);
        } else {
            System.out.println("Enemy blocked! E.D " + enemy.stats.get("defense") + " > A.A " + player.stats.get("attack"));
        }

        if (enemy.stats.get("attack") > player.stats.get("defense")) {
            player.health -= 1;
            System.out.println("Player hit! Lives left: " + player.health);
        } else {
            System.out.println("Player blocked! P.D " + player.stats.get("defense") + " > E.A " + enemy.stats.get("attack"));
        }
        alterStats(false);

        player.selectedDice.clear();
        enemy.selectedDice.clear();

    }

}
