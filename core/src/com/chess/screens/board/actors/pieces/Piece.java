package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.chess.ChessGaphics;

public class Piece extends Actor {

    Texture texture;
    float xCenterPosition;
    float yCenterPosition;
    float width = ChessGaphics.TILE_WIDTH;
    float height = ChessGaphics.TILE_HEIGHT;

    public Piece(Texture texture) {
        this.texture = texture;
    }

    public void setBoardPosition(int i, int j) {
        xCenterPosition = ChessGaphics.BOARD_X_OFFSET + (i + 1 / 2f) * width;
        yCenterPosition = ChessGaphics.BOARD_Y_OFFSET + (j + 1 / 2f) * height;
        setX(xCenterPosition);
        setY(yCenterPosition);
    }
}
