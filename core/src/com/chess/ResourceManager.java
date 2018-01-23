package com.chess;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ResourceManager {

    public final AssetManager assetManager = new AssetManager();

    //    Textures
    private final String greenTile = "GreenTile.png";
    private final String grayTile = "GrayTile.png";
    private final String mainScreenBackground = "MainScreenBackground.png";

    public void queueAddTiles() {
        assetManager.load(greenTile, Texture.class);
        assetManager.load(grayTile, Texture.class);
    }

    public void loadMainScreenBackground() {
        assetManager.load(mainScreenBackground, Texture.class);
    }
}
