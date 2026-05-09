package entity;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Battle {

    Player player;
    Enemy enemy;
    Boolean isPlayerTurn = true;
    Creature[] fighters = new Creature[2];
    ArrayList<Dice> draftDice = new ArrayList<>();

    Scanner scan = new Scanner(System.in);
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
            System.out.println("entity.Player died.");
        } else {
            System.out.println("entity.Enemy died.");
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

        int playerChoice = 0;

        while (!draftDice.isEmpty()) {
            if (isPlayerTurn) {
                for (int i = 0; i < draftDice.size(); i++) {
                    System.out.println(i + "." + draftDice.get(i).type + "\t" + draftDice.get(i).value);
                }
                displayCombatStats();

                try {
                    playerChoice = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please input an integer");
                    scan.next();
                    draftPeriod();
                }
            }

            try {
                player.selectedDice.add(draftDice.remove(playerChoice));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Choose a valid number");
                draftPeriod();
            }
            // TODO implement enemy attack logic
            try {
                enemy.selectedDice.add(draftDice.remove(0));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("What");
            }
            System.out.println("Remaining dice: " + draftDice.size());
        }

        attack();

    }

    public void alterStats(boolean isAttacking) {
        int statChange;
        for (Creature fighter : fighters) {
            for (Dice die : fighter.selectedDice) {
                statChange = isAttacking ? die.value : -die.value;
                // entity.Dice change the stats of the fighter themselves, then change back after attacking.
                fighter.stats.put(die.type,
                        fighter.stats.get(die.type) + statChange);
            }
        }
    }

    public void displayCombatStats() {
        System.out.println("--------------------------------------------------");
        for (Creature fighter : fighters) {
            System.out.println(fighter.name
                    + "\nAttack\t" + fighter.stats.get(Dice.DieType.ATTACK)
                    + "\nDefense\t" + fighter.stats.get(Dice.DieType.DEFENSE)
                    + "\nFocus\t" + fighter.stats.get(Dice.DieType.FOCUS)
                    + "\nSpeed\t" + fighter.stats.get(Dice.DieType.SPEED));
            System.out.println("--------------------------------------------------\n");
        }

    }

    public void attackSelect() {
        int index = 0;
        ArrayList<Creature.Ability> availableAbilities = new ArrayList<>();

        System.out.println("Available abilities");
        for (Creature.Ability ability : player.abilities) {
            System.out.println(index + ". " + ability.name + "\t"
                    + ability.effect);
            availableAbilities.add(ability);
            index++;
        }
        scan.nextInt();

    }

    // The round ends, and damage is dealt
    public void attack() {
        alterStats(true);
        attackSelect();
        if (player.stats.get(Dice.DieType.ATTACK) > enemy.stats.get(Dice.DieType.DEFENSE)) {
            enemy.health -= 1;
            System.out.println("Enemy hit! Lives left: " + enemy.health);
            System.out.println("Enemy Defense " + enemy.stats.get(Dice.DieType.DEFENSE)
                        + " < Player Attack  " + player.stats.get(Dice.DieType.ATTACK));
        } else {
            System.out.println("Enemy blocked!");
            System.out.println("Enemy Defense " + enemy.stats.get(Dice.DieType.DEFENSE)
                        + " >= Player Attack " + player.stats.get(Dice.DieType.ATTACK));
        }

        if (enemy.stats.get(Dice.DieType.ATTACK) > player.stats.get(Dice.DieType.DEFENSE)) {
            player.health -= 1;
            System.out.println("Player hit! Lives left: " + player.health);
            System.out.println("Player Defense " + player.stats.get(Dice.DieType.DEFENSE)
                        + " < Enemy Attack " + enemy.stats.get(Dice.DieType.ATTACK));
        } else {
            System.out.println("Player blocked!");
            System.out.println("Player Defense " + player.stats.get(Dice.DieType.DEFENSE)
                        + " >= Enemy Attack " + enemy.stats.get(Dice.DieType.ATTACK));
        }
        alterStats(false);

        player.selectedDice.clear();
        enemy.selectedDice.clear();

    }

}
