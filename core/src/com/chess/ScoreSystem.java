package com.chess;

import com.chess.screens.board.actors.pieces.Piece;

public class ScoreSystem {

    private int blackScore = 0;
    private int whiteScore = 0;

    public void addScore(Piece piece) {
        if (piece.getPlayer() == Chess.PLAYER.WHITES) {
            blackScore += piece.getScore();
        } else {
            whiteScore += piece.getScore();
        }
    }

    public String getBlackScore() {
        return String.valueOf(blackScore);
    }

    public String getWhiteScore() {
        return String.valueOf(whiteScore);
    }
}
