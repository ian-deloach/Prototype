package main;

import entity.Battle;
import entity.Enemy;
import entity.Player;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Player player = new Player();
        Enemy enemy = new Enemy();
        Battle battle = new Battle(player, enemy);
        GameScreen screen = new GameScreen();

        screen.createFrame();


    }
}
