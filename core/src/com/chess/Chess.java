package com.chess;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.chess.screens.board.BoardScreen;
import com.chess.screens.LoadingScreen;
import com.chess.screens.MainMenuScreen;
import com.chess.screens.board.StateMachine;

public class Chess extends Game {

    private MainMenuScreen mainMenuScreen;
    private LoadingScreen loadingScreen;
    private BoardScreen boardScreen;
    public ResourceManager resourceManager = new ResourceManager();

    public final static int MENU = 0;
    public final static int BOARD = 1;

    public enum PLAYER {
        WHITES, BLACKS
    }


    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }

    @Override
    public void render() {
        getScreen().render(Gdx.graphics.getDeltaTime());
        if (getScreen() instanceof BoardScreen) {
            if (((BoardScreen) getScreen()).getStateMachine().getState() == StateMachine.STATE.WHITE_WINS || ((BoardScreen) getScreen()).getStateMachine().getState() == StateMachine.STATE.BLACK_WINS){
                if(((BoardScreen) getScreen()).getTimeInWinState() > 5){
                    getScreen().dispose();
                    changeScreenTo(0);
                }
            }
        }
    }

    @Override
    public void dispose() {
    }

    public void changeScreenTo(int screen) {
        getScreen().dispose();
        switch (screen) {
            case MENU:
                mainMenuScreen = new MainMenuScreen(this);
                this.setScreen(mainMenuScreen);
                break;
            case BOARD:
                boardScreen = new BoardScreen(this);
                this.setScreen(boardScreen);
                break;
        }
    }
}
