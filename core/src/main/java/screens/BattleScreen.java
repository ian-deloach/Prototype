package screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import creatures.Enemy;
import creatures.Player;
import entity.Battle;
import main.Main;

import java.io.IOException;

public class BattleScreen implements Screen {

    final Main game;


    public BattleScreen(final Main game) {
        this.game = game;
    }

    public void setUpBattle() {
        Player player = new Player();
        Enemy enemy = new Enemy();
        Battle battle = null;
        try {
            battle = new Battle(player, enemy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        battle.runBattle();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        game.font.draw(game.batch, "battle screen", 300, 300);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
