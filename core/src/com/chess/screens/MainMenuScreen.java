package com.chess.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.chess.Chess;
import com.chess.screens.board.actors.Background;

public class MainMenuScreen implements Screen {

    private final Stage stage;
    private Chess chess;
    private final Skin skin = new Skin(Gdx.files.internal("skin/skin.json"));

    public MainMenuScreen(final Chess chess) {
        this.chess = chess;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        addActorsToStage();

        Table table = new Table();
        table.setFillParent(true);
        TextButton newGame = new TextButton("New Game", skin);
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                chess.changeScreenTo(Chess.BOARD);
            }
        });
        newGame.pad(10, 20, 10, 20);
        table.add(newGame);
        table.row().pad(30, 0, 30, 0);
        TextButton exit = new TextButton("Exit", skin);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        exit.pad(10, 20, 10, 20);
        table.add(exit);
        stage.addActor(table);
    }

    private void addActorsToStage() {
        chess.resourceManager.loadBoardBackground();
        chess.resourceManager.assetManager.finishLoading();
        Actor background = new Background((Texture) chess.resourceManager.assetManager.get("BoardBackground.png"));
        stage.addActor(background);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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
        stage.dispose();
    }
}
