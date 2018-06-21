package com.chess.screens.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.chess.screens.board.StateMachine;
import com.chess.screens.board.actors.pieces.Piece;

public class AttackTile extends Tile {

    private Piece targetPiece;

    AttackTile(Texture texture, final StateMachine stateMachine, Piece targetPiece) {
        super(texture, stateMachine);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Attack Tile: touch down");
                if(stateMachine.getState() == StateMachine.STATE.MOVE){
                    clickedThis();
                    event.stop();
                    return true;
                }
                return false;
            }
        });
        this.targetPiece = targetPiece;
    }

    private void clickedThis() {
        stateMachine.clickedAttack(this);
    }

    public Piece getTargetPiece() {
        return targetPiece;
    }
}
