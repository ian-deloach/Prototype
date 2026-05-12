package main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import entity.Battle;
import creatures.Enemy;
import creatures.Player;
import screens.BattleScreen;
import java.io.IOException;

public class Main extends Game {

    public BitmapFont font;
    public SpriteBatch batch;

    @Override
    public void create() {
        font = new BitmapFont();
        batch = new SpriteBatch();
        font.setUseIntegerPositions(false);
        this.setScreen(new BattleScreen(this));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
