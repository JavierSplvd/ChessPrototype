package com.chess;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ResourceManager {

    public final AssetManager assetManager = new AssetManager();

    //    Textures
    private final String greenTile = "GreenTile.png";
    private final String grayTile = "GreyTile.png";
    private final String mainScreenBackground = "MainScreenBackground.png";
    private final String WPawn = "WPawn.png";
    private final String WKing = "WKing.png";

    private final String WQueen = "WQueen.png";

    private final String WBishop = "WBishop.png";
    private final String WKnight = "WKnight.png";
    private final String WRook = "WRook.png";
    public void loadMainScreenBackground() {
        assetManager.load(mainScreenBackground, Texture.class);
    }

    public void loadTilesAssets() {
        assetManager.load(greenTile, Texture.class);
        assetManager.load(grayTile, Texture.class);

    }

    public void loadWhitePieces() {
        assetManager.load(WPawn, Texture.class);
        assetManager.load(WKing, Texture.class);
        assetManager.load(WQueen, Texture.class);
        assetManager.load(WBishop, Texture.class);
        assetManager.load(WKnight, Texture.class);
        assetManager.load(WRook, Texture.class);
    }

    public Texture getWPawn() {
        return assetManager.get(WPawn);
    }

    public Texture getWKing() {
        return assetManager.get(WKing);
    }

    public Texture getWQueen() {
        return assetManager.get(WQueen);
    }

    public Texture getWBishop() {
        return assetManager.get(WBishop);
    }

    public Texture getWKnight() {
        return assetManager.get(WKnight);
    }

    public Texture getWRook() {
        return assetManager.get(WRook);
    }
}
