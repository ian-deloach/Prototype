import java.util.ArrayList;
import java.util.Random;

public class Battle {

    Player player;
    Enemy enemy;
    ArrayList<Dice> draftDice = new ArrayList<>();
    Random random = new Random();

    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    // Resets the available dice at the beginning of the round
    public void startRound() {
        System.out.println("New round");
        draftDice.removeAll(draftDice);
        for (int i = 0; i < 6; i++) {
            draftDice.add(new Dice(
                    random.nextInt(4),
                    random.nextInt(6) + 1
            ));
        }

        for (Dice die : draftDice) {
            System.out.println(die.color + ": " + die.value);
        }
    }

    // Where the player and enemy pick dice
    public void draftPeriod() {
        // TODO TEST VALUES. MAKE IT SO YOU CAN ACTUALLY PICK DICE
        player.selectedDice.add(draftDice.get(0));
        draftDice.remove(0);

        if (draftDice.size() <= 0) {attack();}

        // TODO implement enemy attack logic
        enemy.selectedDice.add(draftDice.get(0));

        if (draftDice.size() <= 0) {attack();}
        else {draftPeriod();}

    }

    // After drafting, calculate the stats
    public void calculateStats(Boolean isAttacking) {
        for (Dice die : player.selectedDice) {

        }
    }

    // The round ends, and damage is dealt
    public void attack() {

    }

}
