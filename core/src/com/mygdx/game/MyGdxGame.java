package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Background background;
    private Hero hero;
    private long pastTime;
    private Asteroid[] asteroids;
    static Bullet[] bullets;
    private Texture textureBullet;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
        hero = new Hero();
        pastTime = System.currentTimeMillis();
        asteroids = new Asteroid[30];
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i] = new Asteroid();
        }
        bullets = new Bullet[200];
        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet();
        }
        textureBullet = new Texture("bullet20.png");
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);
        hero.render(batch);
        for (Asteroid asteroid : asteroids) {
            asteroid.render(batch);
        }
        for (Bullet bullet : bullets) {
            if (bullet.isActive()) {
                bullet.render(batch, textureBullet);
            }
        }
        batch.end();
    }

    private void update() {
        long currentTime = System.currentTimeMillis();
        background.update(currentTime - pastTime);
        hero.update(currentTime - pastTime);
        for (Asteroid asteroid : asteroids) {
            asteroid.update(currentTime - pastTime);
        }
        for (Bullet bullet : bullets) {
            if (bullet.isActive()) {
                bullet.update(currentTime - pastTime);
                for (Asteroid asteroid : asteroids) {
                    if (asteroid.getRectangle().contains(bullet.getPosition())) {
                        asteroid.recreate();
                        bullet.destroy();
                        break;
                    }
                }
            }
        }
        pastTime = currentTime;

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            System.out.println(Gdx.graphics.getWidth() + " - " + Gdx.graphics.getHeight());
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
