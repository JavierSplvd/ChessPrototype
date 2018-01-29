package com.chess;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ResourceManager {

    public final AssetManager assetManager = new AssetManager();

    //    Textures
    private final String greenTile = "GreenTile.png";
    private final String grayTile = "GreyTile.png";
    private final String mainScreenBackground = "MainScreenBackground.png";
    private final String boardBackground = "BoardBackground.png";


    private final String WPawn = "WPawn.png";
    private final String WKing = "WKing.png";
    private final String WQueen = "WQueen.png";
    private final String WBishop = "WBishop.png";
    private final String WKnight = "WKnight.png";
    private final String WRook = "WRook.png";

    private final String BPawn = "BPawn.png";
    private final String BKing = "BKing.png";
    private final String BQueen = "BQueen.png";
    private final String BBishop = "BBishop.png";
    private final String BKnight = "BKnight.png";
    private final String BRook = "BRook.png";

    public void loadMainScreenBackground() {
        assetManager.load(mainScreenBackground, Texture.class);
    }

    public void loadBoardBackground(){
        assetManager.load(boardBackground, Texture.class);
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

    public Texture getBPawn() {
        return assetManager.get(BPawn);
    }

    public Texture getBKing() {
        return assetManager.get(BKing);
    }

    public Texture getBQueen() {
        return assetManager.get(BQueen);
    }

    public Texture getBBishop() {
        return assetManager.get(BBishop);
    }

    public Texture getBKnight() {
        return assetManager.get(BKnight);
    }

    public Texture getBRook() {
        return assetManager.get(BRook);
    }

    public void loadBlackPieces() {
        assetManager.load(BPawn, Texture.class);
        assetManager.load(BKing, Texture.class);
        assetManager.load(BQueen, Texture.class);
        assetManager.load(BBishop, Texture.class);
        assetManager.load(BKnight, Texture.class);
        assetManager.load(BRook, Texture.class);
    }

    public Texture getBoardBackground() {
        return assetManager.get(boardBackground);
    }
}
