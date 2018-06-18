package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.chess.Chess;
import com.chess.screens.board.StateMachine;

public class Knight extends Piece {

    public Knight(Chess.PLAYER player, Texture texture, int i, int j, StateMachine stateMachine) {
        super(texture, player,stateMachine);
        setBoardPosition(i, j);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float bottomLeftX = xCenterPosition - width / 2;
        float bottomLeftY = yCenterPosition - height / 2;
        batch.draw(texture, bottomLeftX, bottomLeftY, width, height);
    }
}
