package main;

import entity.Battle;
import creatures.Enemy;
import creatures.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) throws IOException {
        Player player = new Player();
        Enemy enemy = new Enemy();
        Battle battle = new Battle(player, enemy);

        battle.runBattle();

    }
}
