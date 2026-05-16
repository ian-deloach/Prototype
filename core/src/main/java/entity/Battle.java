package entity;

import abilities.Ability;
import creatures.Creature;
import creatures.Enemy;
import creatures.Player;
import main.Main;

import java.io.IOException;
import java.io.InputStream;
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
    InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("logging.properties");

    // TODO Replace Scanner
//    Scanner scan = new Scanner(System.in);
    Random random = new Random();

    public Battle(Player player, Enemy enemy) throws IOException {
        LogManager.getLogManager().readConfiguration(inputStream);
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

    // Resets and prepares the dice/fighters for the next round.
    public void startRound() {
        createDraftDie();
        applyPreparedStats();
//        draftPeriod();
    }

    public void applyPreparedStats() {
        player.getStats().putAll(player.getPreparedStatChanges());
        player.getPreparedStatChanges().clear();
        enemy.getStats().putAll(enemy.getPreparedStatChanges());
        enemy.getPreparedStatChanges().clear();
    }

    // Creates 6 random dice with random types and values.
    public void createDraftDie() {
        draftDice.clear();
        for (int i = 0; i < 6; i++) {
            draftDice.add(new Dice(
                    random.nextInt(4),
                    random.nextInt(6) + 1
            ));
        }
    }

    // The player and the enemy both select their dice here.
    public void draftPeriod() {

        int playerChoice = 0;

        while (!draftDice.isEmpty()) {
            if (isPlayerTurn) {
                displayCombatStats();
                for (int i = 0; i < draftDice.size(); i++) {
                    System.out.println(i + "." + draftDice.get(i).type + "\t" + draftDice.get(i).getValue());
                }

                //TODO Remove this. It only exits the app so it doesn't loop while I fix things.
//                System.exit(0);

                try {
                    // TODO Replace Scanner
//                    playerChoice = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please input an integer");
                    // TODO Replace Scanner
//                    scan.next();
                    draftPeriod();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Something else happened with drafting period", e);
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
                    + "\nEnergy\t" + fighter.getStats().get(Dice.DieType.ENERGY)
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
            if (player.getStats().get(Dice.DieType.ENERGY) >= ability.getCost()) {
                System.out.println(index + ". " + ability.getName() + "\t"
                        + ability.getDescription());
                availableAbilities.add(ability);
                index++;
            }
        }

        try {
            // TODO Replace Scanner
//            selectedAbility = availableAbilities.get(scan.nextInt());
//            selectedAbility.useAbility(player, enemy);
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
            System.exit(0);
        }

        System.out.println("You win!");
        System.exit(0);
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public ArrayList<Dice> getDraftDice() {
        return draftDice;
    }

    public Creature[] getFighters() {
        return fighters;
    }
}
