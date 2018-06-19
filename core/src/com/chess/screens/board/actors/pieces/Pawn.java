package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.chess.Chess;
import com.chess.screens.board.StateMachine;

public class Pawn extends Piece {


    public Pawn(Chess.PLAYER player, Texture texture, int i, int j, StateMachine stateMachine) {
        super(texture, player, stateMachine);
        setBoardPosition(i, j);
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

        if (getPlayer() == Chess.PLAYER.WHITES && !moved) {
            y = getyBoardCoord() + 2;
        } else if (getPlayer() == Chess.PLAYER.BLACKS && !moved) {
            y = getyBoardCoord() - 2;
        }

        if (collisionMap[x][y] == 0) {
            behaviourMap[x][y] = 2;
        }
        return behaviourMap;
    }
}
