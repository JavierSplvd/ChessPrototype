package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.chess.Chess;
import com.chess.screens.board.StateMachine;

public class Pawn extends Piece {

    private boolean twoStepOption;

    public Pawn(Chess.PLAYER player, Texture texture, int i, int j, StateMachine stateMachine) {
        super(texture, player, stateMachine);
        setBoardPosition(i, j);
        twoStepOption = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float bottomLeftX = xCenterPosition - width / 2;
        float bottomLeftY = yCenterPosition - height / 2;
        batch.draw(texture, bottomLeftX, bottomLeftY, width, height);
    }

    @Override
    public int[][] createBehaviourMap(int[][] collisionMap) {
        int x = getxBoardCoord();
        int y;
        int[][] behaviourMap = collisionMap.clone();

        if (getPlayer() == Chess.PLAYER.WHITES) {
            y = getyBoardCoord() + 1;
        } else {
            y = getyBoardCoord() - 1;
        }

        if (collisionMap[x][y] == 0) {
            behaviourMap[x][y] = 2;
        }

        if (getPlayer() == Chess.PLAYER.WHITES && twoStepOption) {
            y = getyBoardCoord() + 2;
            twoStepOption = false;
        } else if (getPlayer() == Chess.PLAYER.BLACKS && twoStepOption) {
            y = getyBoardCoord() - 2;
            twoStepOption = false;
        }

        if (collisionMap[x][y] == 0) {
            behaviourMap[x][y] = 2;
        }
        return behaviourMap;
    }
}
