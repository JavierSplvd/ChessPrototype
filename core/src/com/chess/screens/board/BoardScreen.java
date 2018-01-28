package com.chess.screens.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.chess.Chess;
import com.chess.screens.board.actors.pieces.*;
import com.chess.screens.board.actors.Tile;

public class BoardScreen implements Screen {

    private Chess chess;
    private final Stage stage;

    public BoardScreen(Chess chess) {
        this.chess = chess;
        StretchViewport viewport = new StretchViewport(960, 540);
        stage = new Stage(viewport);
        createTiles();
        createWhitePieces();
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

    private void createWhitePieces() {
        chess.resourceManager.loadWhitePieces();
        chess.resourceManager.assetManager.finishLoading();
        Group whitePieces = new Group();
        for (int p = 0; p < 8; p++) {
            Pawn pawn = new Pawn(chess.resourceManager.getWPawn(), p, 1);
            whitePieces.addActor(pawn);
        }
        King king = new King(chess.resourceManager.getWKing(), 4, 0);
        Queen queen = new Queen(chess.resourceManager.getWQueen(), 3, 0);
        Bishop bishop1 = new Bishop(chess.resourceManager.getWBishop(), 2, 0);
        Bishop bishop2 = new Bishop(chess.resourceManager.getWBishop(), 5, 0);
        Knight knight1 = new Knight(chess.resourceManager.getWKnight(), 1, 0);
        Knight knight2 = new Knight(chess.resourceManager.getWKnight(), 6, 0);
        Rook rook1 = new Rook(chess.resourceManager.getWRook(), 0, 0);
        Rook rook2 = new Rook(chess.resourceManager.getWRook(), 7, 0);
        whitePieces.addActor(king);
        whitePieces.addActor(queen);
        whitePieces.addActor(bishop1);
        whitePieces.addActor(bishop2);
        whitePieces.addActor(knight1);
        whitePieces.addActor(knight2);
        whitePieces.addActor(rook1);
        whitePieces.addActor(rook2);

        stage.addActor(whitePieces);
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
