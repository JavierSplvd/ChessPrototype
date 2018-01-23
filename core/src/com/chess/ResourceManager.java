package com.chess;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ResourceManager {

    public final AssetManager assetManager = new AssetManager();

    //    Textures
    private final String greenTile = "GreenTile.png";
    private final String grayTile = "GreyTile.png";
    private final String mainScreenBackground = "MainScreenBackground.png";

    public void loadMainScreenBackground() {
        assetManager.load(mainScreenBackground, Texture.class);
    }

    public void loadTilesAssets() {
        assetManager.load(greenTile, Texture.class);
        assetManager.load(grayTile, Texture.class);

    }
}
