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

    public String getTextBlackScore() {
        return String.valueOf(blackScore);
    }

    public String getTextWhiteScore() {
        return String.valueOf(whiteScore);
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public int getBlackScore() {
        return blackScore;
    }
}
