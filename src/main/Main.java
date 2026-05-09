package main;

import entity.Battle;
import creatures.Enemy;
import creatures.Player;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Player player = new Player();
        Enemy enemy = new Enemy();
        Battle battle = new Battle(player, enemy);
        GameScreen screen = new GameScreen();

        battle.runBattle();
//        screen.createFrame();

    }
}
