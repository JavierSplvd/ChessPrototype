package com.chess.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.chess.ChessGaphics;

import java.util.Random;

public class Tile extends Actor {

    private Texture texture;
    private float xCenterPosition;
    private float yCenterPosition;
    private float width = ChessGaphics.TILE_WIDTH;
    private float height = ChessGaphics.TILE_HEIGHT;
    private Random r = new Random();
    private float alpha;

    public Tile(Texture texture) {
        this.texture = texture;
        alpha = 0.8f + r.nextFloat() * 0.2f;
    }

    public void setBoardPosition(int i, int j) {
        xCenterPosition = (i + 1 / 2f) * width;
        yCenterPosition = (j + 1 / 2f) * height;
        setX(xCenterPosition);
        setY(yCenterPosition);
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float bottomLeftX = ChessGaphics.BOARD_X_OFFSET + xCenterPosition - width / 2;
        float bottomLeftY = ChessGaphics.BOARD_Y_OFFSET + yCenterPosition - height / 2;
        batch.setColor(1, 1, 1, alpha);
        batch.draw(texture, bottomLeftX, bottomLeftY, width, height);
        batch.setColor(1, 1, 1, 1);
    }
}
