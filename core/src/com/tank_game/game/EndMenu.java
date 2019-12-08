package com.tank_game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank_game.game.Game;

public class EndMenu extends ApplicationAdapter {
    SpriteBatch batch;
    Texture texture;
    boolean isPressed = false;

    public void create() {
        batch = new SpriteBatch();
        texture = new Texture("EndMenu.jpg");
    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }

        batch.begin();
        if (!isPressed) {
            batch.draw(texture, 0, 0);
        }
        batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            isPressed = true;
            Game game = new Game();
        }
    }

    public void dispose() {
    }
}