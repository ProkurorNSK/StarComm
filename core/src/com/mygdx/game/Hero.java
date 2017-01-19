package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class Hero {
    private Vector2 position;
    private Texture texture;
    private float speed;
    private float angle;
    private float speedAngle;
    private float maxAngle;
    private float fireRate;
    private float fireCounter;

    Hero() {
        position = new Vector2(100, 100);
        texture = new Texture("ship80x60.tga");
        speed = 720.0f;
        speedAngle = 2.0f;
        angle = 0.0f;
        maxAngle = 40.0f;
        fireRate = 5.0f;
        fireCounter = 1 / fireRate;
    }

    void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, texture.getWidth() / 2, texture.getHeight() / 2, texture.getWidth(), texture.getHeight(), 1f, 1f, angle, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    void update(long dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += speed * dt / 1000;
            if (position.y > Gdx.graphics.getHeight()) {
                position.y = -texture.getHeight();
            }
            angle += speedAngle;
            if (angle > maxAngle) {
                angle = maxAngle;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed * dt / 1000;
            if (position.y < -texture.getHeight()) {
                position.y = Gdx.graphics.getHeight();
            }
            angle -= speedAngle;
            if (angle < -maxAngle) {
                angle = -maxAngle;
            }

        }

        if (angle > 0) {
            angle -= speedAngle / 2;
        }
        if (angle < 0) {
            angle += speedAngle / 2;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed * dt / 1000;
            if (position.x < 0) {
                position.x = 0;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed * dt / 1000;
            if (position.x > Gdx.graphics.getWidth() - texture.getWidth()) {
                position.x = Gdx.graphics.getWidth() - texture.getWidth();
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fireCounter += (float) dt / 1000;
            if (fireCounter > 1 / fireRate) {
                fireCounter = 0;
                fire();
            }
        }
    }

    private void fire() {
        MyGdxGame.bullets.add(new Bullet(position.x + texture.getWidth(), position.y + texture.getHeight() / 2));
    }
}
