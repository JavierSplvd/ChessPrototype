package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.chess.Chess;
import com.chess.screens.board.StateMachine;

public class Rook extends Piece {

    public Rook(Chess.PLAYER player, Texture texture, int i, int j, StateMachine stateMachine) {
        super(texture, player, stateMachine);
        setBoardPosition(i, j);
        setScore(5);
    }
}