package com.chess.screens;

import com.badlogic.gdx.Screen;
import com.chess.Chess;

public class LoadingScreen implements Screen {

    private Chess chess;

    public LoadingScreen(Chess chess) {
        this.chess = chess;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        chess.changeScreenTo(Chess.MENU);
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
