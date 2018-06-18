package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.chess.Chess;
import com.chess.screens.board.StateMachine;

public class King extends Piece {

    public King(Chess.PLAYER player, Texture texture, int i, int j, StateMachine stateMachine) {
        super(texture, player, stateMachine);
        setBoardPosition(i, j);
    }

    @Override
    public int[][] createBehaviourMap(int[][] collisionMap) {
        int x = getxBoardCoord();
        int y = getyBoardCoord();
        int[][] behaviourMap = collisionMap.clone();

        if (x + 1 < 8 && y + 1 < 8) {
            if (collisionMap[x + 1][y + 1] == 0) {
                behaviourMap[x + 1][y + 1] = 2;
            }
        }

        if (x + 1 < 8 && y < 8) {
            if (collisionMap[x + 1][y] == 0) {
                behaviourMap[x + 1][y] = 2;
            }
        }


        if (x + 1 < 8 && y - 1 >= 0) {
            if (collisionMap[x + 1][y - 1] == 0) {
                behaviourMap[x + 1][y - 1] = 2;
            }
        }

        if (x < 8 && y - 1 >= 0) {
            if (collisionMap[x][y - 1] == 0) {
                behaviourMap[x][y - 1] = 2;
            }
        }

        if (x - 1 >= 0 && y - 1 >= 0) {
            if (collisionMap[x - 1][y - 1] == 0) {
                behaviourMap[x - 1][y - 1] = 2;
            }
        }

        if (x - 1 >= 0 && y >= 0) {
            if (collisionMap[x - 1][y] == 0) {
                behaviourMap[x - 1][y] = 2;
            }
        }

        if (x - 1 >= 0 && y + 1 < 8) {
            if (collisionMap[x - 1][y + 1] == 0) {
                behaviourMap[x - 1][y + 1] = 2;
            }
        }

        if (x < 8 && y + 1 < 8) {
            if (collisionMap[x][y + 1] == 0) {
                behaviourMap[x][y + 1] = 2;
            }
        }
        return behaviourMap;
    }
}
