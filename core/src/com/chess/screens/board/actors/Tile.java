package com.chess.screens.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.chess.ChessGaphics;

import java.util.Random;

public class Tile extends Actor {

    private Texture texture;
    private int xBoardCoord;
    private int yBoardCoord;
    private float xCenterPosition;
    private float yCenterPosition;
    private float width = ChessGaphics.TILE_WIDTH;
    private float height = ChessGaphics.TILE_HEIGHT;
    private Random r = new Random();
    private float alpha;
    private float xBottomLeft;
    private float yBottomLeft;

    public Tile(Texture texture) {
        this.texture = texture;
        alpha = 0.8f + r.nextFloat() * 0.2f;
        alpha = 1;
        setSize(width, height);
    }

    public void setBoardPosition(int i, int j) {
        xBoardCoord = i;
        yBoardCoord = j;
        xCenterPosition = ChessGaphics.BOARD_X_OFFSET + (i + 1 / 2f) * width;
        yCenterPosition = ChessGaphics.BOARD_Y_OFFSET + (j + 1 / 2f) * height;
        xBottomLeft = xCenterPosition - width / 2;
        yBottomLeft = yCenterPosition - height / 2;
        setX(xBottomLeft);
        setY(yBottomLeft);
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float bottomLeftX = xCenterPosition - width / 2;
        float bottomLeftY = yCenterPosition - height / 2;
        batch.setColor(1, 1, 1, alpha);
        batch.draw(texture, bottomLeftX, bottomLeftY, width, height);
        batch.setColor(1, 1, 1, 1);
    }

    public int getxBoardCoord() {
        return xBoardCoord;
    }

    public int getyBoardCoord() {
        return yBoardCoord;
    }
}
