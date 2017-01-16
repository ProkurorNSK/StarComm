package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class Background {

    class Star {
        static final float SPEED_MAX = 500.0f;
        static final float SPEED_MIN = 60.0f;
        private Vector2 position;
        private float speed;

        Star() {
            position = new Vector2((float) Math.random() * Gdx.graphics.getWidth(), (float) Math.random() * Gdx.graphics.getHeight());
            speed = SPEED_MIN + (float) Math.random() * SPEED_MAX;
        }

        void update(long dt) {
            position.x -= speed * dt / 1000;
            if (position.x < 0) {
                position.x = Gdx.graphics.getWidth();
                position.y = (float) Math.random() * Gdx.graphics.getHeight();
                speed = SPEED_MIN + (float) Math.random() * SPEED_MAX;
            }
        }
    }

    private Texture texture;
    private Texture textureStar;
    private Star[] stars;

    Background() {
        texture = new Texture("staticback.jpg");
        textureStar = new Texture("star12.tga");
        int STARS_COUNT = 400;
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }

    void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
        for (Star star : stars) {
            batch.draw(textureStar, star.position.x, star.position.y);
        }

    }

    void update(long dt) {
        for (Star star : stars) {
            star.update(dt);
        }
    }
}
