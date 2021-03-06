package com.chess.screens.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.chess.screens.board.StateMachine;

public class MovementTile extends Tile {


    MovementTile(Texture texture, final StateMachine stateMachine) {
        super(texture, stateMachine);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Movement Dot: touch down");
                if(stateMachine.getState() == StateMachine.STATE.MOVE){
                    clickedThis();
                    event.stop();
                    return true;
                }
                return false;
            }
        });
    }
    private void clickedThis(){
        stateMachine.clicked(this);
    }
}
