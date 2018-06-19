package com.chess.screens.board.actors.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.chess.Chess;
import com.chess.ChessGaphics;
import com.chess.screens.board.StateMachine;

public class Piece extends Actor {

    Texture texture;
    int xBoardCoord;
    int yBoardCoord;
    float xCoordTemp;
    float yCoordTemp;
    float xCenterPosition;
    float yCenterPosition;
    float width = ChessGaphics.TILE_WIDTH;
    float height = ChessGaphics.TILE_HEIGHT;
    boolean moved = false;
    private float yBottomLeft;
    private float xBottomLeft;
    private Chess.PLAYER player;
    private StateMachine stateMachine;

    public Piece(Texture texture, Chess.PLAYER player, StateMachine stateMachine) {
        this.texture = texture;
        this.player = player;
        this.stateMachine = stateMachine;
        setSize(width, height);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Piece: touch down");
                chooseThisPiece();
                event.stop();
                return true;
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        xCoordTemp = xCoordTemp + 0.1f * (xBottomLeft - xCoordTemp);
        yCoordTemp = yCoordTemp + 0.1f * (yBottomLeft - yCoordTemp);
        setX(xCoordTemp);
        setY(yCoordTemp);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, xCoordTemp, yCoordTemp, width, height);
    }

    void setBoardPosition(int i, int j) {
        xBoardCoord = i;
        yBoardCoord = j;
        xCenterPosition = ChessGaphics.BOARD_X_OFFSET + (i + 1 / 2f) * width;
        yCenterPosition = ChessGaphics.BOARD_Y_OFFSET + (j + 1 / 2f) * height;
        xBottomLeft = xCenterPosition - width / 2;
        yBottomLeft = yCenterPosition - height / 2;
    }

    private void chooseThisPiece() {
        stateMachine.clicked(this);
    }

    public int[][] createBehaviourMap(int[][] collisionMap) {
        int columns = 8;
        int rows = 8;
        int[][] behaviourMap = new int[columns][rows];
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                behaviourMap[x][y] = 1;
            }
        }
        return behaviourMap;
    }

    public Chess.PLAYER getPlayer() {
        return player;
    }

    public int getxBoardCoord() {
        return xBoardCoord;
    }

    public int getyBoardCoord() {
        return yBoardCoord;
    }

    public void changeBoardPosition(int i, int j) {
        setBoardPosition(i,j);
        moved = true;
    }
}
