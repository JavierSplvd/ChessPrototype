package com.chess;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.chess.screens.board.BoardScreen;
import com.chess.screens.LoadingScreen;
import com.chess.screens.MainMenuScreen;

public class Chess extends Game {

    private MainMenuScreen mainMenuScreen;
    private LoadingScreen loadingScreen;
    private BoardScreen boardScreen;
    public ResourceManager resourceManager = new ResourceManager();

    public final static int MENU = 0;
    public final static int BOARD = 1;

    public final static int WHITES = 0;
    public final static int BLACKS = 1;


    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }

    @Override
    public void render() {
        getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
    }

    public void changeScreenTo(int screen) {
        getScreen().dispose();
        switch (screen) {
            case MENU:
                if (mainMenuScreen == null) {
                    mainMenuScreen = new MainMenuScreen(this);
                }
                this.setScreen(mainMenuScreen);
                break;
            case BOARD:
                if (boardScreen == null) {
                    boardScreen = new BoardScreen(this);
                }
                this.setScreen(boardScreen);
                break;
        }
    }
}
