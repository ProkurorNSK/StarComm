package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

class Background {

    class Star {
        private Vector2 position;
        private float speed;

        public Star() {
            position = new Vector2((float) Math.random() * 1280, (float) Math.random() * 1280);
            speed = 5.0f;
        }

        public void update() {
            position.x -=  speed;
            if (position.x < 0) {
                position.x = 1280;
                position.y = (float) Math.random() * 720;
            }
        }
    }

    private Texture texture;
    private Texture textureStar;
    private Star[] stars;
    private final int STARS_COUNT = 200;

    Background() {
        texture = new Texture("staticback.jpg");
        textureStar = new Texture("star12.tga");
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i] = new Star();
        }
    }

    void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
        for (Star star: stars) {
            batch.draw(textureStar, star.position.x, star.position.y);
            star.update();
        }

    }
}
