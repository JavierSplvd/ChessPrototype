package com.chess.screens.board;

import com.chess.Chess;
import com.chess.screens.board.actors.AttackTile;
import com.chess.screens.board.actors.MovementTile;
import com.chess.screens.board.actors.MovementSystem;
import com.chess.screens.board.actors.Tile;
import com.chess.screens.board.actors.pieces.Piece;

import java.util.ArrayList;

public class StateMachine {

    private ArrayList<Piece> pieceList;
    private Chess.PLAYER playerTurn;

    private int turnCount = 0;
    private STATE currentState;
    StateMachineUI ui;

    private Piece pieceSelected;
    private Piece targetPiece;

    private MovementSystem movementSystem;


    public enum STATE {
        CHOOSE, MOVE;

    }

    public StateMachine(ArrayList<Piece> pieceList) {
        this.pieceList = pieceList;
        playerTurn = Chess.PLAYER.WHITES;
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
        ui.update(playerTurn, currentState, getTurnCount());
    }

    private void changeTurn() {
        turnCount += 1;
        if (playerTurn == Chess.PLAYER.WHITES) {
            playerTurn = Chess.PLAYER.BLACKS;
        } else if (playerTurn == Chess.PLAYER.BLACKS) {
            playerTurn = Chess.PLAYER.WHITES;
        }
        pieceSelected = null;
    }

    public void returnToChooseState() {
        currentState = STATE.CHOOSE;
        movementSystem.clear();
        updateUI();
    }

    public void setMovementSystem(MovementSystem movementSystem) {
        this.movementSystem = movementSystem;
    }

    public String getPlayerTurn() {
        return playerTurn.toString();
    }

    public STATE getState(){
        return currentState;
    }

    public String getCurrentState() {
        return currentState.toString();
    }

    public String getTurnCount() {
        return String.valueOf(turnCount);
    }

    public void clicked(Piece piece) {
        if (currentState == STATE.CHOOSE && piece.getPlayer() == playerTurn) {
            System.out.println("State Machine: Piece selected");
            this.pieceSelected = piece;
            createMovTiles();
            SoundSystem.play(SoundSystem.SOUND_KEYS.CLICK);
        } else {
            returnToChooseState();
            SoundSystem.play(SoundSystem.SOUND_KEYS.REJECT_CLICK);
        }
    }

    public void clicked(MovementTile movementTile) {
        movePieceTo(movementTile.getxBoardCoord(), movementTile.getyBoardCoord());
    }

    public void clicked(Tile tile) {
        returnToChooseState();
    }

    private void createMovTiles() {
        movementSystem.createNewMovTiles(pieceSelected, this);
    }

    public void movePieceTo(int i, int j) {
        if (currentState == STATE.MOVE) {
            pieceSelected.changeBoardPosition(i, j);
            movementSystem.clear();
            nextState();
        }
    }

    public void clickedAttack(AttackTile attackTile) {
        System.out.println("Attack");
        targetPiece = attackTile.getTargetPiece();
        pieceList.remove(targetPiece);
        targetPiece.remove();
        movePieceTo(attackTile.getxBoardCoord(), attackTile.getyBoardCoord());
        movementSystem.clear();
        SoundSystem.play(SoundSystem.SOUND_KEYS.ATTACK);
    }
}
