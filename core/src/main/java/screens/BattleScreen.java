package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import creatures.Enemy;
import creatures.Player;
import entity.Battle;
import main.Main;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;

public class BattleScreen implements Screen {

    final Main game;
    private FitViewport viewport;
    private ShapeRenderer shapeRend;
    private OrthographicCamera camera;
    Battle battle;
    private Stage stage;
    private Table table;
    private boolean isDraftAreaCreated = false;

    AssetLibrary library = new AssetLibrary();

    public BattleScreen(final Main game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        camera.position.set(400, 300, 0);
        camera.update();
        library.loadBattleAssets();
        stage = new Stage(viewport, game.spriteBatch);
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
        game.defaultFont.draw(game.spriteBatch, String.valueOf(battle.getPlayer().getAttack()), 90, 540);

        game.defaultFont.draw(game.spriteBatch, "DEF", 55, 517);
        game.defaultFont.draw(game.spriteBatch, String.valueOf(battle.getPlayer().getDefense()), 90, 517);

        game.defaultFont.draw(game.spriteBatch, "SPD", 55, 494);
        game.defaultFont.draw(game.spriteBatch, String.valueOf(battle.getPlayer().getSpeed()), 90, 494);

        game.defaultFont.draw(game.spriteBatch, "ENG", 55,471);
        game.defaultFont.draw(game.spriteBatch, String.valueOf(battle.getPlayer().getEnergy()), 90,471);
        game.spriteBatch.end();
    }

    public void makePlayerSkills() {
        // TODO Replace with stages and UI images
        shapeRend.begin(ShapeRenderer.ShapeType.Line);
        shapeRend.setColor(Color.BLUE);
        shapeRend.rect(25, 150, 150, 250);
        shapeRend.end();
    }

    public void makeEnemyStats() {
        // TODO Replace with stages and UI images
        shapeRend.begin(ShapeRenderer.ShapeType.Line);
        shapeRend.setColor(Color.RED);
        shapeRend.rect(450, 450, 300, 100);
        shapeRend.end();
    }

    public void makeEnemySkills() {
        // TODO Replace with stages and UI images
        shapeRend.begin(ShapeRenderer.ShapeType.Line);
        shapeRend.setColor(Color.ORANGE);
        shapeRend.rect(625, 150, 150, 250);
        shapeRend.end();
    }

    public void makeDraftArea() {
        shapeRend.begin(ShapeRenderer.ShapeType.Line);
        shapeRend.setColor(Color.GREEN);
        shapeRend.rect(340, 150, 120, 238);
        shapeRend.end();

        // The coordinates for the specific die being placed.
        int x = 350;
        int y = 346;
        String fileName;
        Image dieImage = null;

        for (int i = 0; i < battle.getDraftDice().size(); i++) {
            System.out.println("i = " + i);
            fileName = "dice/"
                        + battle.getDraftDice().get(i).getType().toLowerCase()
                        + battle.getDraftDice().get(i).getValue()
                        + ".png";

            dieImage = new Image(library.getManager().get(fileName, Texture.class));


            int finalI = i;
            Image finalDieImage = dieImage;
            dieImage.addListener((new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    battle.handlePlayerTurn(finalI);
                    isDraftAreaCreated = false;
                    finalDieImage.remove();
                }

            }));

            dieImage.setBounds(x, y, 32, 32);

            if (i % 2 == 0) {
                x = 418;
            } else {
                x = 350;
                y -= 46;
            }
            // TODO This is currently re-adding the last actor to the stage even when removing one
            if (battle.getStateAsString() != "DONE") {
                stage.addActor(dieImage);
            }
        }

    }

    public void makeItemBar() {
        // TODO Replace with stages and UI images
        shapeRend.begin(ShapeRenderer.ShapeType.Line);
        shapeRend.setColor(Color.PURPLE);
        shapeRend.rect(100, 75, 600, 65);
        shapeRend.end();
    }

    public void makeRelicBar() {
        // TODO Replace with stages and UI images
        shapeRend.begin(ShapeRenderer.ShapeType.Line);
        shapeRend.setColor(Color.PINK);
        shapeRend.rect(150, 25, 500, 50);
        shapeRend.end();
    }

    @Override
    public void show() {
        setUpBattle();
        Gdx.input.setInputProcessor(stage);
        shapeRend = new ShapeRenderer();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);

        if (library.getManager().update()) {

            game.spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
            shapeRend.setProjectionMatrix(viewport.getCamera().combined);
            viewport.apply();
            // makeGrid() should go FIRST so everything else is rendered on top
            makeGrid();
            makePlayerStats();
            makePlayerSkills();
            makeEnemyStats();
            makeEnemySkills();
            makeItemBar();
            makeRelicBar();

            if (!isDraftAreaCreated) {
                makeDraftArea();
                isDraftAreaCreated = true;
            }

            stage.act();
            stage.draw();

            game.spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
            game.spriteBatch.begin();

            // Player
            game.spriteBatch.draw(library.getManager().get("images/placeholderMC.jpg", Texture.class),
                    200, 150, 115, 175);

            game.spriteBatch.end();

        }

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
        library.getManager().dispose();
    }
}
