package com.tank_game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;


public class Wall extends ApplicationAdapter {
    private SpriteBatch batch = new SpriteBatch();
    private String wallImgPath = "wall.jpg";
    public Texture wallImg = new Texture(Gdx.files.internal(wallImgPath));;
    public Polygon collision;
    public float width = 10;
    public float height;
    public float angle;

    public Wall(float x, float y, float angle, float height, float width) {
        this.width = width;
        this.height = height;
        collision = new Polygon(new float[]{0,0,width,0,width,this.height,0,this.height});
        collision.setOrigin(width/2, this.height/2);
        collision.setPosition(x, y);
        this.angle = angle;
        collision.setRotation(angle);
    }

    public void step(){
        batch.begin();
        batch.draw(wallImg, collision.getX(), collision.getY(), width/2, height/2, width, height,
                1, 1, angle, 0, 0, (int)width, (int)height, false, false);
        batch.end();
    }
}