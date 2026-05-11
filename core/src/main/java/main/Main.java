package main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import entity.Battle;
import creatures.Enemy;
import creatures.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class Main extends ApplicationAdapter {

    public static void main(String[] args) throws IOException {
        Player player = new Player();
        Enemy enemy = new Enemy();
        Battle battle = new Battle(player, enemy);
        Main main = new Main();

        battle.runBattle();

    }
    private SpriteBatch batch;
    private Texture image;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
