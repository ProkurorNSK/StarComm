package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

class Asteroid {
    private static final float SPEED_MAX = 500.0f;
    private static final float SPEED_MIN = 100.0f;
    private Vector2 position;
    private float speed;
    private static Texture texture;

    Asteroid() {
        position = new Vector2((float) (Math.random() + 1) * Gdx.graphics.getWidth(), (float) Math.random() * Gdx.graphics.getHeight());
        speed = SPEED_MIN + (float) Math.random() * SPEED_MAX;
        if (texture == null) {
            texture = new Texture("asteroid60.tga");
        }
    }

    Rectangle getRectangle() {
        return new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    void recreate() {
        position.x = (float) (Math.random() + 1) * Gdx.graphics.getWidth();
        position.y = (float) Math.random() * Gdx.graphics.getHeight();
        speed = SPEED_MIN + (float) Math.random() * SPEED_MAX;
    }

    void update(long dt) {
        position.x -= speed * dt / 1000;
        if (position.x < -texture.getWidth()) {
            recreate();
        }
    }

    void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }
}
