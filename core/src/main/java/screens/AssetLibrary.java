package screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetLibrary {

    private AssetManager manager = new AssetManager();

    public void loadBattleAssets() {
        int x = 1;
        // loads every variety of die
        while (x < 7) {
            manager.load("dice/attack" + x + ".png", Texture.class);
            manager.load("dice/defense" + x + ".png", Texture.class);
            manager.load("dice/speed" + x + ".png", Texture.class);
            manager.load("dice/energy" + x + ".png", Texture.class);
            x++;
        }
        manager.load("images/placeholderMC.jpg", Texture.class);
    }

    public AssetManager getManager() {
        return manager;
    }
}
