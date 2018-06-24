package com.chess.screens.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.chess.Chess;

public class StateMachineUI {

    private final Skin skin = new Skin(Gdx.files.internal("skin/skin.json"));
    private Table table;
    private Label player;
    private Label state;
    private Label turnCount;
    private Label whiteScore;
    private Label blackScore;

    public StateMachineUI(StateMachine stateMachine) {
        table = new Table(skin);
        table.setFillParent(true);
        player = new Label(stateMachine.getPlayerTurn(), skin);
        state = new Label(stateMachine.getCurrentState(), skin);
        turnCount = new Label(stateMachine.getTurnCount(), skin);
        whiteScore = new Label(stateMachine.getScoreSystem().getWhiteScore(),skin);
        blackScore = new Label(stateMachine.getScoreSystem().getBlackScore(),skin);
        table.add(player).pad(0, 20, 0, 20);
        table.add(state).pad(0, 20, 0, 20);
        table.add(turnCount);
        table.add(whiteScore).pad(0, 20, 0, 20);
        table.add(blackScore).pad(0, 20, 0, 20);
        table.setPosition(-Gdx.graphics.getWidth() * 0.3f, Gdx.graphics.getHeight() * .45f);
    }

    public Table getUI() {
        return table;
    }

    public void update(Chess.PLAYER player, StateMachine.STATE currentState, String turnCount, String newWhiteScore, String newBlackScore) {
        this.player.setText("" + player);
        this.state.setText(currentState.toString());
        this.turnCount.setText(turnCount);
        this.whiteScore.setText(newWhiteScore);
        this.blackScore.setText(newBlackScore);
    }
}
