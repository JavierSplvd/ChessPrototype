package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.chess.Chess;
import com.chess.screens.board.StateMachine;

public class Bishop extends Piece {

    public Bishop(Chess.PLAYER player, Texture texture, int i, int j, StateMachine stateMachine) {
        super(texture, player, stateMachine);
        setBoardPosition(i, j);
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
        int y = getyBoardCoord();
        int[][] behaviourMap = collisionMap.clone();

        //  Diagonal NE
        int i = x + 1;
        int j = y + 1;
        while (i < 8 && j < 8 && i >= 0 && j >= 0) {
            if (collisionMap[i][j] == 0) {
                behaviourMap[i][j] = 2;
            } else {
                break;
            }
            i++;
            j++;
        }
        // Diagonal SE
        i = x + 1;
        j = y - 1;
        while (i < 8 && j < 8 && i >= 0 && j >= 0) {
            if (collisionMap[i][j] == 0) {
                behaviourMap[i][j] = 2;
            } else {
                break;
            }
            i++;
            j--;
        }
        // Diagonal NW
        i = x - 1;
        j = y + 1;
        while (i < 8 && j < 8 && i >= 0 && j >= 0) {
            if (collisionMap[i][j] == 0) {
                behaviourMap[i][j] = 2;
            } else {
                break;
            }
            i--;
            j++;
        }
        // Diagonal SW
        i = x - 1;
        j = y - 1;
        while (i < 8 && j < 8 && i >= 0 && j >= 0) {
            if (collisionMap[i][j] == 0) {
                behaviourMap[i][j] = 2;
            } else {
                break;
            }
            i--;
            j--;
        }

        return behaviourMap;
    }
}
