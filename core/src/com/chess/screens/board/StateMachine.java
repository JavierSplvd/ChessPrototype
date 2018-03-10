package com.chess.screens.board;

import com.chess.Chess;
import com.chess.screens.board.actors.MovementTiles;
import com.chess.screens.board.actors.pieces.Piece;

public class StateMachine {

    private Chess.PLAYER playerTurn;

    private int turnCount = 0;
    private STATE currentState;
    StateMachineUI ui;

    private Piece pieceSelected;

    private MovementTiles movementTiles;



    public enum STATE {
        CHOOSE, MOVE;
    }
    public StateMachine() {
        playerTurn = Chess.PLAYER.WHITES;
        currentState = STATE.CHOOSE;
        ui = new StateMachineUI(this);
    }

    private void nextState() {
        if (currentState == STATE.CHOOSE) {
            currentState = STATE.MOVE;
        } else if (currentState == STATE.MOVE) {
            changeTurn();
            currentState = STATE.CHOOSE;
        }
        updateUI();
    }

    private void updateUI() {
        ui.update(playerTurn, currentState, getTurnCount());
    }

    private void changeTurn() {
        turnCount += 1;
        if (playerTurn == Chess.PLAYER.WHITES) {
            playerTurn = Chess.PLAYER.BLACKS;
        } else if (playerTurn == Chess.PLAYER.BLACKS) {
            playerTurn = Chess.PLAYER.WHITES;
        }
    }

    public void setMovementTiles(MovementTiles movementTiles) {
        this.movementTiles = movementTiles;
    }

    public String getPlayerTurn() {
        return playerTurn.toString();
    }

    public String getCurrentState() {
        return currentState.toString();
    }

    public String getTurnCount() {
        return String.valueOf(turnCount);
    }

    public void selectPiece(Piece piece) {
        if (currentState == STATE.CHOOSE && piece.getPlayer() == playerTurn) {
            System.out.println("Piece selected");
            this.pieceSelected = piece;
            createMovTiles();
            nextState();
        }
    }

    private void createMovTiles() {
        movementTiles.createNewMovTiles(pieceSelected);
    }
}
