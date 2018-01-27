package com.chess.screens.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.chess.ChessGaphics;

public class Pawn extends Piece {

    public Pawn(Texture texture, int i, int j) {
        super(texture);
        setBoardPosition(i, j);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float bottomLeftX = xCenterPosition - width / 2;
        float bottomLeftY = yCenterPosition - height / 2;
        batch.draw(texture, bottomLeftX, bottomLeftY, width, height);
    }

}
