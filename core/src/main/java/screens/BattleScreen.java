package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    private ShapeRenderer shape;
    private OrthographicCamera camera;
    private Texture sampleD6;

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
        Battle battle = null;
        try {
            battle = new Battle(player, enemy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        battle.runBattle();
    }

    public void setUpRectangles() {
        shape.setProjectionMatrix(viewport.getCamera().combined);
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(Color.WHITE);
        // Put rectangle at 200x 200y with the last two being the size in pixels
        shape.rect(200, 200, 10,10);
        shape.end();
    }

    @Override
    public void show() {
        shape = new ShapeRenderer();
        sampleD6 = new Texture("sampleD6.png");
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);

        viewport.apply();
        setUpRectangles();

        game.batch.setProjectionMatrix(viewport.getCamera().combined);
        shape.setProjectionMatrix(viewport.getCamera().combined);
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(Color.RED);

        // Border around the viewport
        shape.rect(1, 1, 799, 599);

        shape.end();

        // Draw text in corners
        game.batch.setProjectionMatrix(viewport.getCamera().combined);
        game.batch.begin();
        game.font.draw(game.batch, "battle screen", 300, 300);
        game.batch.draw(sampleD6, 100, 100, 12, 12);

        game.batch.end();
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
        shape.dispose();
        sampleD6.dispose();
    }
}
