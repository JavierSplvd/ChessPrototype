package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.chess.Chess;
import com.chess.screens.board.StateMachine;

public class Knight extends Piece {

    public Knight(Chess.PLAYER player, Texture texture, int i, int j, StateMachine stateMachine) {
        super(texture, player, stateMachine);
        setBoardPosition(i, j);
    }

    @Override
    public int[][] createBehaviourMap(int[][] collisionMap) {
        int x = getxBoardCoord();
        int y = getyBoardCoord();
        int[][] behaviourMap = collisionMap.clone();

        int originalX = 1;
        int originalY = 2;
        int dx;
        int dy;
        for (int i = 0; i < 360; i = i + 90) {
            dx = (int) Math.round(Math.cos(Math.toRadians(i)) * originalX - Math.sin(Math.toRadians(i)) * originalY);
            dy = (int) Math.round(Math.sin(Math.toRadians(i)) * originalX + Math.cos(Math.toRadians(i)) * originalY);
            if (x + dx < 8 && x + dx >= 0 && y + dy < 8 && y + dy >= 0) {
                if (collisionMap[x + dx][y + dy] == 0) {
                    behaviourMap[x + dx][y + dy] = 2;
                }
            }
        }
        originalX = -1;
        originalY = 2;
        for (int i = 0; i <= 360; i = i + 90) {
            dx = (int) Math.round(Math.cos(Math.toRadians(i)) * originalX - Math.sin(Math.toRadians(i)) * originalY);
            dy = (int) Math.round(Math.sin(Math.toRadians(i)) * originalX + Math.cos(Math.toRadians(i)) * originalY);
            if (x + dx < 8 && x + dx >= 0 && y + dy < 8 && y + dy >= 0) {
                if (collisionMap[x + dx][y + dy] == 0) {
                    behaviourMap[x + dx][y + dy] = 2;
                }
            }
        }

        return behaviourMap;
    }

}
