package main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import entity.Battle;
import creatures.Enemy;
import creatures.Player;
import screens.BattleScreen;
import java.io.IOException;

public class Main extends Game {

    public BitmapFont defaultFont;
    public SpriteBatch spriteBatch;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        this.setScreen(new BattleScreen(this));

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/defaultFont.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.magFilter = Texture.TextureFilter.Linear;
        defaultFont = generator.generateFont(parameter);
        defaultFont.setColor(Color.WHITE);
        defaultFont.setUseIntegerPositions(false);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        spriteBatch.dispose();
        defaultFont.dispose();
    }

}
