package com.chess.screens.board;

import com.badlogic.gdx.graphics.Texture;
import com.chess.Chess;
import com.chess.ResourceManager;
import com.chess.screens.board.BoardScreen;
import com.chess.screens.board.actors.pieces.*;

public class PieceFactory {

    private BoardScreen boardScreen;
    private ResourceManager resourceManager;

    public PieceFactory(BoardScreen boardScreen, ResourceManager resourceManager) {
        this.boardScreen = boardScreen;
        this.resourceManager = resourceManager;
    }

    public Pawn createPawn(int player, int x, int y) {
        Texture texture;
        if (player == Chess.WHITES) {
            texture = resourceManager.getWPawn();
        } else {
            texture = resourceManager.getBPawn();
        }
        Pawn pawn = new Pawn(texture, x, y);
        return pawn;
    }

    public King createKing(int player) {
        Texture texture;
        King king;
        if (player == Chess.WHITES) {
            texture = resourceManager.getWKing();
            king = new King(texture, 3, 0);
        } else {
            texture = resourceManager.getBKing();
            king = new King(texture, 3, 7);
        }
        return king;
    }

    public Queen createQueen(int player) {
        Texture texture;
        Queen queen;
        if (player == Chess.WHITES) {
            texture = resourceManager.getWQueen();
            queen = new Queen(texture, 4, 0);
        } else {
            texture = resourceManager.getBQueen();
            queen = new Queen(texture, 4, 7);
        }
        return queen;
    }

    public Rook createRook(int player, int x, int y) {
        Texture texture;
        Rook rook;
        if (player == Chess.WHITES) {
            texture = resourceManager.getWRook();
            rook = new Rook(texture, x, y);
        } else {
            texture = resourceManager.getBRook();
            rook = new Rook(texture, x, y);
        }
        return rook;
    }

    public Knight createKnight(int player, int x, int y) {
        Texture texture;
        Knight knight;
        if (player == Chess.WHITES) {
            texture = resourceManager.getWKnight();
            knight = new Knight(texture, x, y);
        } else {
            texture = resourceManager.getBKnight();
            knight = new Knight(texture, x, y);
        }
        return knight;
    }

    public Bishop createBishop(int player, int x, int y) {
        Texture texture;
        Bishop bishop;
        if (player == Chess.WHITES) {
            texture = resourceManager.getWBishop();
            bishop = new Bishop(texture, x, y);
        } else {
            texture = resourceManager.getBBishop();
            bishop = new Bishop(texture, x, y);
        }
        return bishop;
    }

}
