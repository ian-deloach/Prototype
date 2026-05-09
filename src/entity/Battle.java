package entity;

import abilities.Ability;
import creatures.Creature;
import creatures.Enemy;
import creatures.Player;

import java.util.*;

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
        player.getStats().putAll(player.getBaseStats());
        fighters[1] = enemy;
        enemy.getStats().putAll(enemy.getBaseStats());
    }

    public void runBattle() {

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            startRound();
        }

        if (player.getHealth() <= 0) {
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
        System.out.println("--------------------------------------------------");
        draftPeriod();
    }

    // Where the player and enemy pick dice
    public void draftPeriod() {

        int playerChoice = 0;

        while (!draftDice.isEmpty()) {
            if (isPlayerTurn) {
                displayCombatStats();
                for (int i = 0; i < draftDice.size(); i++) {
                    System.out.println(i + "." + draftDice.get(i).type + "\t" + draftDice.get(i).getValue());
                }

                try {
                    playerChoice = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please input an integer");
                    scan.next();
                    draftPeriod();
                }
            }

            try {
                alterStats(player, draftDice.get(playerChoice));
                player.getSelectedDice().add(draftDice.remove(playerChoice));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Choose a valid number");
                draftPeriod();
            }
            // TODO implement enemy attack logic
            try {
                alterStats(enemy, draftDice.get(0));
                enemy.getSelectedDice().add(draftDice.remove(0));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("What");
            }
            System.out.println("Remaining dice: " + draftDice.size());
        }

        attack();

    }

    public void alterStats(Creature fighter, Dice selectedDie) {
        int statChange = selectedDie.getValue();
        fighter.getStats().put(selectedDie.type,
                fighter.getStats().get(selectedDie.type) + statChange);
    }

    public void displayCombatStats() {
        System.out.println("--------------------------------------------------");
        for (Creature fighter : fighters) {
            System.out.println(fighter.getName()
                    + "\nAttack\t" + fighter.getStats().get(Dice.DieType.ATTACK)
                    + "\nDefense\t" + fighter.getStats().get(Dice.DieType.DEFENSE)
                    + "\nFocus\t" + fighter.getStats().get(Dice.DieType.FOCUS)
                    + "\nSpeed\t" + fighter.getStats().get(Dice.DieType.SPEED));
            System.out.println("--------------------------------------------------\n");
        }

    }

    public void abilitySelect() {
        int index = 0;
        Ability selectedAbility;
        ArrayList<Ability> availableAbilities = new ArrayList<>();

        displayCombatStats();

        System.out.println("Available abilities");
        for (Ability ability : player.getAbilities()) {
            if (player.getStats().get(Dice.DieType.FOCUS) >= ability.getCost()) {
                System.out.println(index + ". " + ability.getName() + "\t"
                        + ability.getDescription());
                availableAbilities.add(ability);
                index++;
            }
        }

        try {
            selectedAbility = availableAbilities.get(scan.nextInt());

            Dice.DieType currentStat;
            for (Map.Entry<Dice.DieType, Integer> stat : selectedAbility.getStatChanges().entrySet()) {
                currentStat = stat.getKey();
                player.getStats().put(currentStat,
                        player.getStats().get(currentStat) + stat.getValue());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Choose a valid number.");
            abilitySelect();
        } catch (Exception e) {
            System.out.println("Something else went wrong with the weapon selection.");
        }


    }

    // The round ends, and damage is dealt
    public void attack() {
        abilitySelect();
        displayCombatStats();
        if (player.getStats().get(Dice.DieType.ATTACK) > enemy.getStats().get(Dice.DieType.DEFENSE)) {
            enemy.setHealth(enemy.getHealth() - 1);
            System.out.println("Enemy hit! Lives left: " + enemy.getHealth());
            System.out.println("Enemy Defense " + enemy.getStats().get(Dice.DieType.DEFENSE)
                        + " < Player Attack  " + player.getStats().get(Dice.DieType.ATTACK));
        } else {
            System.out.println("Enemy blocked!");
            System.out.println("Enemy Defense " + enemy.getStats().get(Dice.DieType.DEFENSE)
                        + " >= Player Attack " + player.getStats().get(Dice.DieType.ATTACK));
        }

        if (enemy.getStats().get(Dice.DieType.ATTACK) > player.getStats().get(Dice.DieType.DEFENSE)) {
            player.setHealth(player.getHealth() - 1);
            System.out.println("Player hit! Lives left: " + player.getHealth());
            System.out.println("Player Defense " + player.getStats().get(Dice.DieType.DEFENSE)
                        + " < Enemy Attack " + enemy.getStats().get(Dice.DieType.ATTACK));
        } else {
            System.out.println("Player blocked!");
            System.out.println("Player Defense " + player.getStats().get(Dice.DieType.DEFENSE)
                        + " >= Enemy Attack " + enemy.getStats().get(Dice.DieType.ATTACK));
        }

        // Reset fighters for the next drafting round
        player.getSelectedDice().clear();
        player.getStats().putAll(player.getBaseStats());
        enemy.getSelectedDice().clear();
        enemy.getStats().putAll(enemy.getBaseStats());

    }

}
