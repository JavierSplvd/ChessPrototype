package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.chess.Chess;
import com.chess.ChessGaphics;

public class Piece extends Actor {

    Texture texture;
    float xCenterPosition;
    float yCenterPosition;
    float width = ChessGaphics.TILE_WIDTH;
    float height = ChessGaphics.TILE_HEIGHT;
    private float yBottomLeft;
    private float xBottomLeft;
    private Chess.PLAYER side;

    public Piece(Texture texture, Chess.PLAYER side) {
        this.texture = texture;
        this.side = side;
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
}
