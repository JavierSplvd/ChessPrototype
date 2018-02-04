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

    public StateMachineUI(StateMachine stateMachine) {
        table = new Table(skin);
        table.setFillParent(true);
        player = new Label(stateMachine.getPlayer(), skin);
        state = new Label(stateMachine.getCurrentState(), skin);
        table.add(player).pad(0, 20, 0, 20);
        table.add(state);
        table.setPosition(-Gdx.graphics.getWidth() * 0.4f, Gdx.graphics.getHeight() * .45f);
    }

    public Table getUI() {
        return table;
    }

    public void update(Chess.PLAYER player, StateMachine.STATE currentState) {
        this.player.setText("" + player);
        this.state.setText(currentState.toString());
    }
}
