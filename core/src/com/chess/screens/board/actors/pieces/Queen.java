package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.chess.Chess;
import com.chess.screens.board.StateMachine;

public class Queen extends Piece {

    public Queen(Chess.PLAYER player, Texture texture, int i, int j, StateMachine stateMachine) {
        super(texture, player, stateMachine);
        setBoardPosition(i, j);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Queen");
                chooseThisPiece();
                return false;
            }
        });
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
        // Direction N
        i = x;
        j = y + 1;
        while (i < 8 && j < 8 && i >= 0 && j >= 0) {
            if (collisionMap[i][j] == 0) {
                behaviourMap[i][j] = 2;
            } else {
                break;
            }
            j++;
        }
        // Direction S
        i = x;
        j = y - 1;
        while (i < 8 && j < 8 && i >= 0 && j >= 0) {
            if (collisionMap[i][j] == 0) {
                behaviourMap[i][j] = 2;
            } else {
                break;
            }
            j--;
        }
        // Direction E
        i = x + 1;
        j = y;
        while (i < 8 && j < 8 && i >= 0 && j >= 0) {
            if (collisionMap[i][j] == 0) {
                behaviourMap[i][j] = 2;
            } else {
                break;
            }
            i++;
        }
        // Direction W
        i = x - 1;
        j = y;
        while (i < 8 && j < 8 && i >= 0 && j >= 0) {
            if (collisionMap[i][j] == 0) {
                behaviourMap[i][j] = 2;
            } else {
                break;
            }
            i--;
        }

        return behaviourMap;
    }
}
