package com.chess.screens.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.chess.screens.board.StateMachine;

public class MovementDot extends Tile {

    private StateMachine stateMachine;

    public MovementDot(Texture texture, final StateMachine stateMachine) {
        super(texture);
        this.stateMachine = stateMachine;
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Clicked movement dot.");
                stateMachine.movePieceTo(getxBoardCoord(), getyBoardCoord());
                return true;
            }
        });
    }
}
