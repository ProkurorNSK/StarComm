package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class Bullet {
    private Vector2 position;
    private float speed;
    private boolean active;

    Bullet() {
        position = new Vector2(0.0f, 0.0f);
        speed = 400.0f;
        active = false;
    }

    boolean isActive() {
        return active;
    }

    Vector2 getPosition() {
        return position;
    }

    void destroy() {
        active = false;
    }

    void setup(float x, float y) {
        active = true;
        position.x = x;
        position.y = y;
    }

    void update(long dt) {
        position.x += speed * dt / 1000;
        if (position.x > Gdx.graphics.getWidth()) {
            destroy();
        }
    }

    void render(SpriteBatch batch, Texture texture) {
        batch.draw(texture, position.x, position.y);
    }
}
