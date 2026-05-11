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

    public FitViewport viewport;
    public BitmapFont font;
    public SpriteBatch batch;

    @Override
    public void create() {
        viewport = new FitViewport(800, 600);
        font = new BitmapFont();
        batch = new SpriteBatch();

        font.setUseIntegerPositions(false);
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

        this.setScreen(new BattleScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }


}
