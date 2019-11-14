package com.tank_game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet  extends ApplicationAdapter {
    private SpriteBatch batch = new SpriteBatch();

    public Rectangle collision;
    public float angle;

    int timer = 0; // For counting up

    public Bullet(float x, float y, float angle) {
        collision = new Rectangle();
        collision.x = x;
        collision.y = y;
        collision.width = 8;
        collision.height = 8;
        this.angle=angle;
    }
}
