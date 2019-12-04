package com.tank_game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Bullet  extends ApplicationAdapter {
    private SpriteBatch batch = new SpriteBatch();

    public Polygon collision;
    public float angle;
    public float width = 10;
    public float height = 10;

    int timer = 0; // For counting up

    public Bullet(float x, float y, float angle) {
        collision = new Polygon(new float[]{0,0,width,0,width,height,0,height});
        collision.setOrigin(width/2, height/2);
        collision.setPosition(x,y);
        this.angle=angle;
    }
}
