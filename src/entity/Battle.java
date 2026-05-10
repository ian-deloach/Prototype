package entity;

import abilities.Ability;
import creatures.Creature;
import creatures.Enemy;
import creatures.Player;
import main.Main;

import java.io.IOException;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Battle {

    //Logging Stuff
    private static final Logger logger = Logger.getLogger(Battle.class.getName());

    Player player;
    Enemy enemy;
    Boolean isPlayerTurn = true;
    Creature[] fighters = new Creature[2];
    ArrayList<Dice> draftDice = new ArrayList<>();

    Scanner scan = new Scanner(System.in);
    Random random = new Random();

    public Battle(Player player, Enemy enemy) throws IOException {
        LogManager.getLogManager().readConfiguration(
                Main.class.getResourceAsStream("/logging.properties")
        );
        this.player = player;
        this.enemy = enemy;
        fighters[0] = player;
        player.getStats().putAll(player.getBaseStats());
        fighters[1] = enemy;
        enemy.getStats().putAll(enemy.getBaseStats());
    }

    // Starts the fight. Pre-round stuff should be done here in the future.
    public void runBattle() {
        startRound();
    }

    // Resets the available dice at the beginning of the round
    public void startRound() {
        createDraftDie();
        applyPreparedStats();
        draftPeriod();
    }

    public void applyPreparedStats() {
        player.getStats().putAll(player.getPreparedStatChanges());
        player.getPreparedStatChanges().clear();
        enemy.getStats().putAll(enemy.getPreparedStatChanges());
        enemy.getPreparedStatChanges().clear();
    }

    public void createDraftDie() {
        draftDice.clear();
        for (int i = 0; i < 6; i++) {
            draftDice.add(new Dice(
                    random.nextInt(4),
                    random.nextInt(6) + 1
            ));
        }
        System.out.println("Draft size: " + draftDice.size());
        System.out.println("--------------------------------------------------");
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
                    + "\nSpeed\t" + fighter.getStats().get(Dice.DieType.SPEED)
                    + "\nHealth\t" + fighter.getHealth());
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
            selectedAbility.useAbility(player, enemy);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Choose a valid number.");
            abilitySelect();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something else happened with ability selection.", e);
            abilitySelect();
        }


    }

    // The round ends, and damage is dealt
    public void attack() {
        abilitySelect();
        displayCombatStats();
        endRound();
        if ((player.getHealth() > 0) && enemy.getHealth() > 0) {startRound();}
        else {endBattle();};
    }

    public void endRound() {
        // Reset fighters for the next drafting round
        player.getSelectedDice().clear();
        player.getStats().putAll(player.getBaseStats());
        enemy.getSelectedDice().clear();
        enemy.getStats().putAll(enemy.getBaseStats());
    }

    public void endBattle() {
        if (player.getHealth() <= 0) {
            System.out.println("Player has died...");
            return;
        }

        System.out.println("You win!");
        System.exit(0);
    }

}
