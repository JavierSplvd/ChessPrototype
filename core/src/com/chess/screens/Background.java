package com.chess.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.sql.BatchUpdateException;

public class Background extends Actor {

    private Texture texture;

    public Background(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }
}
