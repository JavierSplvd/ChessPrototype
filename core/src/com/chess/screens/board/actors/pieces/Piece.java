package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.chess.Chess;
import com.chess.ChessGaphics;
import com.chess.screens.board.StateMachine;

public class Piece extends Actor {

    Texture texture;
    float xCenterPosition;
    float yCenterPosition;
    float width = ChessGaphics.TILE_WIDTH;
    float height = ChessGaphics.TILE_HEIGHT;
    private float yBottomLeft;
    private float xBottomLeft;
    private Chess.PLAYER player;
    private StateMachine stateMachine;

    public Piece(Texture texture, Chess.PLAYER player, StateMachine stateMachine) {
        this.texture = texture;
        this.player = player;
        this.stateMachine = stateMachine;
        setSize(width, height);
    }

    public void setBoardPosition(int i, int j) {
        xCenterPosition = ChessGaphics.BOARD_X_OFFSET + (i + 1 / 2f) * width;
        yCenterPosition = ChessGaphics.BOARD_Y_OFFSET + (j + 1 / 2f) * height;
        xBottomLeft = xCenterPosition - width / 2;
        yBottomLeft = yCenterPosition - height / 2;
        setX(xBottomLeft);
        setY(yBottomLeft);
    }

    void choose() {
        stateMachine.selectPiece(this);
    }

    public Chess.PLAYER getPlayer() {
        return player;
    }
}
