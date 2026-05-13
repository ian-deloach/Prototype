package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import creatures.Enemy;
import creatures.Player;
import entity.Battle;
import main.Main;

import java.io.IOException;

public class BattleScreen implements Screen {

    final Main game;
    private FitViewport viewport;
    private ShapeRenderer shapeRend;
    private OrthographicCamera camera;
    private Texture sampleD6;
    Battle battle;

    public BattleScreen(final Main game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        camera.position.set(400, 300, 0);
        camera.update();
    }

    public void setUpBattle() {
        Player player = new Player();
        Enemy enemy = new Enemy();
        try {
            battle = new Battle(player, enemy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        battle.runBattle();
    }

    // For helping with screen creation
    public void makeGrid() {
        Gdx.gl.glLineWidth(1f);
        shapeRend.begin(ShapeRenderer.ShapeType.Line);
        shapeRend.setColor(Color.GRAY);
        // Vertical lines
        int xCord = 0;
        while (xCord < 800) {
            shapeRend.line(xCord, 0, xCord, 600);
            //Change this value make lines appear at every x value
            xCord += 100;
        }

        int yCord = 0;
        while (yCord < 600) {
            shapeRend.line(0, yCord, 800, yCord);
            yCord += 100;
        }

        shapeRend.rect(1, 1, 799, 599);

        shapeRend.end();
    }

    public void makePlayerStats() {
        shapeRend.begin(ShapeRenderer.ShapeType.Line);
        shapeRend.setColor(Color.SKY);
        shapeRend.rect(50, 450, 300, 100);
        shapeRend.end();

        game.spriteBatch.begin();
        game.defaultFont.draw(game.spriteBatch, battle.getPlayer().getName(), 50, 570);
        game.defaultFont.draw(game.spriteBatch, "ATK", 55, 540);
        game.defaultFont.draw(game.spriteBatch, String.valueOf(battle.getPlayer().getAttack()) + "0000", 90, 540);

        game.defaultFont.draw(game.spriteBatch, "DEF", 55, 517);
        game.defaultFont.draw(game.spriteBatch, String.valueOf(battle.getPlayer().getDefense()), 90, 517);

        game.defaultFont.draw(game.spriteBatch, "SPD", 55, 494);
        game.defaultFont.draw(game.spriteBatch, String.valueOf(battle.getPlayer().getSpeed()), 90, 494);

        game.defaultFont.draw(game.spriteBatch, "ENG", 55,471);
        game.defaultFont.draw(game.spriteBatch, String.valueOf(battle.getPlayer().getEnergy()), 90,471);
        game.spriteBatch.end();
    }

    public void makeRectangles() {
        shapeRend.begin(ShapeRenderer.ShapeType.Line);
        shapeRend.setColor(Color.WHITE);
        // Put rectangle at 200x 200y with the last two being the size in pixels
        shapeRend.rect(200, 200, 100,10);
        shapeRend.end();
    }

    @Override
    public void show() {
        setUpBattle();
        shapeRend = new ShapeRenderer();
        sampleD6 = new Texture("sampleD6.png");
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);
        game.spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        shapeRend.setProjectionMatrix(viewport.getCamera().combined);
        viewport.apply();
        // makeGrid() should go FIRST so everything else is rendered on top
        makeGrid();
        makeRectangles();
        makePlayerStats();

        // Draw text in corners
        game.spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        game.spriteBatch.begin();
        game.defaultFont.draw(game.spriteBatch, "battle screen", 300, 300);
        game.spriteBatch.draw(sampleD6, 100, 100, 16, 16);

        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        shapeRend.dispose();
        sampleD6.dispose();
    }
}
