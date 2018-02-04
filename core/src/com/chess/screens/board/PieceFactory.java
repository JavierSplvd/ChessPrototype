package com.chess.screens.board;

import com.badlogic.gdx.graphics.Texture;
import com.chess.Chess;
import com.chess.ResourceManager;
import com.chess.screens.board.actors.pieces.*;

public class PieceFactory {

    private BoardScreen boardScreen;
    private ResourceManager resourceManager;

    public PieceFactory(BoardScreen boardScreen, ResourceManager resourceManager) {
        this.boardScreen = boardScreen;
        this.resourceManager = resourceManager;
    }

    public Pawn createPawn(Chess.PLAYER player, int x, int y) {
        Texture texture;
        if (player == Chess.PLAYER.WHITES) {
            texture = resourceManager.getWPawn();
        } else {
            texture = resourceManager.getBPawn();
        }
        Pawn pawn = new Pawn(player, texture, x, y);
        return pawn;
    }

    public King createKing(Chess.PLAYER player) {
        Texture texture;
        King king;
        if (player == Chess.PLAYER.WHITES) {
            texture = resourceManager.getWKing();
            king = new King(player, texture, 3, 0);
        } else {
            texture = resourceManager.getBKing();
            king = new King(player, texture, 3, 7);
        }
        return king;
    }

    public Queen createQueen(Chess.PLAYER player) {
        Texture texture;
        Queen queen;
        if (player == Chess.PLAYER.WHITES) {
            texture = resourceManager.getWQueen();
            queen = new Queen(player, texture, 4, 0);
        } else {
            texture = resourceManager.getBQueen();
            queen = new Queen(player, texture, 4, 7);
        }
        return queen;
    }

    public Rook createRook(Chess.PLAYER player, int x, int y) {
        Texture texture;
        Rook rook;
        if (player == Chess.PLAYER.WHITES) {
            texture = resourceManager.getWRook();
            rook = new Rook(player, texture, x, y);
        } else {
            texture = resourceManager.getBRook();
            rook = new Rook(player, texture, x, y);
        }
        return rook;
    }

    public Knight createKnight(Chess.PLAYER player, int x, int y) {
        Texture texture;
        Knight knight;
        if (player == Chess.PLAYER.WHITES) {
            texture = resourceManager.getWKnight();
            knight = new Knight(player, texture, x, y);
        } else {
            texture = resourceManager.getBKnight();
            knight = new Knight(player, texture, x, y);
        }
        return knight;
    }

    public Bishop createBishop(Chess.PLAYER player, int x, int y) {
        Texture texture;
        Bishop bishop;
        if (player == Chess.PLAYER.WHITES) {
            texture = resourceManager.getWBishop();
            bishop = new Bishop(player, texture, x, y);
        } else {
            texture = resourceManager.getBBishop();
            bishop = new Bishop(player, texture, x, y);
        }
        return bishop;
    }

}
