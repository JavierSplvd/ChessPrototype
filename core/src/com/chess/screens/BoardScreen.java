package com.chess.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.chess.Chess;

public class BoardScreen implements Screen {

    private Chess chess;
    private final Stage stage;

    public BoardScreen(Chess chess) {
        this.chess = chess;
        StretchViewport viewport = new StretchViewport(960,540);
        stage = new Stage(viewport);
        createTiles();
    }

    private void createTiles() {
        chess.resourceManager.loadTilesAssets();
        chess.resourceManager.assetManager.finishLoading();
        Group boardTiles = new Group();
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {
                Tile tile = new Tile(getTextureGivenIndexes(column, row));
                tile.setBoardPosition(column, row);
                boardTiles.addActor(tile);
            }
        }
        stage.addActor(boardTiles);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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

    public Texture getTextureGivenIndexes(int column, int row) {
        int sum = column + row;
        if (sum % 2 == 0) {
            return chess.resourceManager.assetManager.get("GreyTile.png");
        } else {
            return chess.resourceManager.assetManager.get("GreenTile.png");

        }
    }
}
