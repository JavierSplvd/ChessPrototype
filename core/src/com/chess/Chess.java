package com.chess;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.chess.screens.LoadingScreen;
import com.chess.screens.MainMenuScreen;

public class Chess extends Game {

    private MainMenuScreen mainMenuScreen;
    private LoadingScreen loadingScreen;

    public final static int MENU = 0;


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
        switch (screen) {
            case MENU:
                if (mainMenuScreen == null) {
                    mainMenuScreen = new MainMenuScreen(this);
                }
                this.setScreen(mainMenuScreen);
                break;
        }
    }
}
