package com.chess.screens.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.chess.Chess;
import com.chess.screens.board.actors.Background;
import com.chess.screens.board.actors.Tile;
import com.chess.screens.board.actors.pieces.*;

public class BoardScreen implements Screen {

    private Chess chess;
    private Stage stage;
    private final PieceFactory pieceFactory;
    private StateMachine stateMachine;

    public BoardScreen(Chess chess) {
        this.chess = chess;
        pieceFactory = new PieceFactory(this, chess.resourceManager);
        stateMachine = new StateMachine();
        createStage();
        createBackground();
        createTiles();
        initializeMovementTiles();
        createWhitePieces();
        createBlackPieces();
        stage.addActor(stateMachine.ui.getUI());
    }

    private void createStage() {
        StretchViewport viewport = new StretchViewport(960, 540);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clicked stage");
                stateMachine.nextState();
            }
        });
    }

    private void initializeMovementTiles() {
        Group movementTiles = new Group();
        stage.addActor(movementTiles);
    }

    private void createBackground() {
        chess.resourceManager.loadBoardBackground();
        chess.resourceManager.assetManager.finishLoading();
        Background background = new Background(chess.resourceManager.getBoardBackground());
        stage.addActor(background);
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
            Pawn pawn = pieceFactory.createPawn(Chess.PLAYER.WHITES, p, 1);
            whitePieces.addActor(pawn);
        }
        King king = pieceFactory.createKing(Chess.PLAYER.WHITES);
        Queen queen = pieceFactory.createQueen(Chess.PLAYER.WHITES);
        Bishop bishop1 = pieceFactory.createBishop(Chess.PLAYER.WHITES, 2, 0);
        Bishop bishop2 = pieceFactory.createBishop(Chess.PLAYER.WHITES, 5, 0);
        Knight knight1 = pieceFactory.createKnight(Chess.PLAYER.WHITES, 1, 0);
        Knight knight2 = pieceFactory.createKnight(Chess.PLAYER.WHITES, 6, 0);
        Rook rook1 = pieceFactory.createRook(Chess.PLAYER.WHITES, 0, 0);
        Rook rook2 = pieceFactory.createRook(Chess.PLAYER.WHITES, 7, 0);
        whitePieces.addActor(king);
        whitePieces.addActor(queen);
        whitePieces.addActor(bishop1);
        whitePieces.addActor(bishop2);
        whitePieces.addActor(knight1);
        whitePieces.addActor(knight2);
        whitePieces.addActor(rook1);
        whitePieces.addActor(rook2);

        whitePieces.debugAll();
        stage.addActor(whitePieces);
    }

    private void createBlackPieces() {
        chess.resourceManager.loadBlackPieces();
        chess.resourceManager.assetManager.finishLoading();
        Group blackPieces = new Group();
        for (int p = 0; p < 8; p++) {
            Pawn pawn = pieceFactory.createPawn(Chess.PLAYER.BLACKS, p, 6);
            blackPieces.addActor(pawn);
        }
        King king = pieceFactory.createKing(Chess.PLAYER.BLACKS);
        Queen queen = pieceFactory.createQueen(Chess.PLAYER.BLACKS);
        Bishop bishop1 = pieceFactory.createBishop(Chess.PLAYER.BLACKS, 2, 7);
        Bishop bishop2 = pieceFactory.createBishop(Chess.PLAYER.BLACKS, 5, 7);
        Knight knight1 = pieceFactory.createKnight(Chess.PLAYER.BLACKS, 1, 7);
        Knight knight2 = pieceFactory.createKnight(Chess.PLAYER.BLACKS, 6, 7);
        Rook rook1 = pieceFactory.createRook(Chess.PLAYER.BLACKS, 0, 7);
        Rook rook2 = pieceFactory.createRook(Chess.PLAYER.BLACKS, 7, 7);
        blackPieces.addActor(king);
        blackPieces.addActor(queen);
        blackPieces.addActor(bishop1);
        blackPieces.addActor(bishop2);
        blackPieces.addActor(knight1);
        blackPieces.addActor(knight2);
        blackPieces.addActor(rook1);
        blackPieces.addActor(rook2);

        blackPieces.debugAll();
        stage.addActor(blackPieces);
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
            return chess.resourceManager.assetManager.get("GreenTile.png");
        } else {
            return chess.resourceManager.assetManager.get("GreyTile.png");

        }
    }
}
