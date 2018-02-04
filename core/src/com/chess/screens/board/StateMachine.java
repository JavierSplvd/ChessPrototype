package com.chess.screens.board;

import com.chess.Chess;

public class StateMachine {

    private Chess.PLAYER player;

    private int turnCount = 0;
    private STATE currentState;
    public StateMachineUI ui;


    public enum STATE {
        CHOOSE, MOVE;
    }
    public StateMachine() {
        player = Chess.PLAYER.WHITES;
        currentState = STATE.CHOOSE;
        ui = new StateMachineUI(this);
    }

    public void nextState() {
        if (currentState == STATE.CHOOSE) {
            currentState = STATE.MOVE;
        } else if (currentState == STATE.MOVE) {
            changeTurn();
            currentState = STATE.CHOOSE;
        }
        updateUI();
    }

    private void updateUI() {
        ui.update(player, currentState);
    }

    public void changeTurn() {
        turnCount += 1;
        if (player == Chess.PLAYER.WHITES) {
            player = Chess.PLAYER.BLACKS;
        } else if (player == Chess.PLAYER.BLACKS) {
            player = Chess.PLAYER.WHITES;
        }
    }

    public String getPlayer() {
        return player.toString();
    }

    public String getCurrentState() {
        return currentState.toString();
    }
}
