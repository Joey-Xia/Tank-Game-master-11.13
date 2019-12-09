package com.tank_game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Tank2 extends ApplicationAdapter {
    private SpriteBatch batch = new SpriteBatch();

    public Texture tankImg;
    private Texture canonImg;
    public Texture bulletImg;
    public Texture explodeImg;
    public Texture regameImg;
    public Polygon collision;

    public Sound fire;
    public Sound exp;

    public float angle;
    public float width = 40;
    public float height = 60;

    public boolean dead;
    public boolean stop;
    public boolean regame;
    public boolean explode;

    //tank's bullets array list
    public ArrayList<Bullet> bullets;

    int timer = 0; // For counting up
    int bullet_cooldown = 50; // To limit the amount of bullets a tank can shoot each unit time

    public Tank2(String tankImgPath, String canonImgPath, String bulletImgPath, String explodeImgPath, String regameImgPath, String fireSdPath, String explodeSdPath, int x, int y) {
        tankImg = new Texture(Gdx.files.internal(tankImgPath));
        canonImg = new Texture(Gdx.files.internal(canonImgPath));
        bulletImg = new Texture(Gdx.files.internal(bulletImgPath));
        explodeImg = new Texture(Gdx.files.internal(explodeImgPath));
        regameImg = new Texture(Gdx.files.internal(regameImgPath));
        fire = Gdx.audio.newSound(Gdx.files.internal(fireSdPath));
        exp = Gdx.audio.newSound(Gdx.files.internal(explodeSdPath));
        collision = new Polygon(new float[]{0,0,width,0,width,height,0,height});
        collision.setOrigin(width/2, height/2);
        collision.setPosition(x, y);
        //initialize cannon
        angle = 0;
        collision.setRotation(angle);
        //initialize bullets
        bullets = new ArrayList<Bullet>();
        //tank's status
        dead = false;
        stop = true;
        regame = false;
        explode = false;
    }

    public void step(Tank player, ArrayList<Wall> map) {
        movement(player, map);
        //mouse_point();
        bullet_movement(map);
        timer++;
        bullet_cooldown++;

        // Draws all the textures in the game
        batch.begin();

        if (!bullets.isEmpty()){
            for (Bullet bullet: bullets) {
                batch.draw(bulletImg, bullet.collision.getX(), bullet.collision.getY(), 10, 10);
            }
        }

        //batch.draw(tankImg, collision.getX(), collision.getY(), width/2, height/2, width, height, 1, 1,
                //angle, 0, 0, 109, 172, false, false);

        //batch.draw(canonImg, collision.getX() + 24, collision.getY() + 24, 8, 8, 16, 64,
                //1, 1, angle, 0, 0, 16, 64, false, false);

        if(dead){
            stop = true;
        }

        if (dead){
            batch.draw(explodeImg, collision.getX()-16, collision.getY()-16, 76, 76);
        }
        if(!dead && !player.show_menu){
            batch.draw(tankImg, collision.getX(), collision.getY(), width/2, height/2, width, height, 1, 1,
                    angle, 0, 0, 109, 172, false, false);
        }
        if (regame){
            batch.draw(regameImg, 0, 0, 400, 400, 800, 800, 1,1, 0, 0, 0, 800, 800, false, false);
        }

        batch.end();
    }

    public void movement(Tank player, ArrayList<Wall> map) {
        // Keyboard Movement
        /*
        if(Gdx.input.isKeyPressed(Input.Keys.A)) collision.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D)) collision.x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.W)) collision.y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.S)) collision.y -= 200 * Gdx.graphics.getDeltaTime();
         */

        if (dead || player.dead) {
            regame = true;
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                regame = false;
                stop = false;
                player.stop = false;
                dead = false;
                player.dead = false;
                collision.setPosition(600, 100);
                player.collision.setPosition(200, 700);
                angle = 0;
                player.angle = 0;
                collision.setRotation(angle);
                player.collision.setRotation(player.angle);
                bullets.clear();
                player.bullets.clear();
                explode = false;
                player.explode = false;
            }
        }

        float temp_x = collision.getX();
        float temp_y = collision.getY();
        float temp_angle = angle;
        boolean isoverlap = false;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !stop) {
            angle += 120 * Gdx.graphics.getDeltaTime();
            angle %= 360;
            collision.setRotation(angle);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !stop) {
            angle -= 120 * Gdx.graphics.getDeltaTime();
            if (angle<0){
                angle += 360;
            }
            angle %= 360;
            collision.setRotation(angle);
        }

        float angle_temp = angle;
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && !stop){
            collision.translate((float)(-200 * Math.sin(Math.toRadians(angle_temp)) * Gdx.graphics.getDeltaTime()),
                    (float)(200 * Math.cos(Math.toRadians(angle_temp)) * Gdx.graphics.getDeltaTime()));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && !stop){
            collision.translate((float)(200 * Math.sin(Math.toRadians(angle_temp)) * Gdx.graphics.getDeltaTime()),
                    (float)(-200 * Math.cos(Math.toRadians(angle_temp)) * Gdx.graphics.getDeltaTime()));
        }


        // Restricts the tank to go outside of the window
        /*
        if(collision.getX() < 0) collision.setPosition(0, collision.getY());
        if(collision.getX() > 800 - 64) collision.setPosition(800 - 64, collision.getY());
        if(collision.getY() < 0) collision.setPosition(collision.getX(), 0);
        if(collision.getY() > 800 - 64) collision.setPosition(collision.getX(), 800-64);
        
         */

        for(Wall wall: map){
            if(Intersector.overlapConvexPolygons(wall.collision, collision)){
                isoverlap = true;
                break;
            }
        }

        if(Intersector.overlapConvexPolygons(player.collision, collision)){
            isoverlap = true;
        }

        if(isoverlap){
            collision.setPosition(temp_x, temp_y);
            angle = temp_angle;
        }

        for (Bullet bullet: player.bullets){
            if (Intersector.overlapConvexPolygons(collision,bullet.collision)){
                dead = true;
            }
        }

    }

    public void bullet_movement(ArrayList<Wall> map) {
        //update the location of all the bullet
        ArrayList<Bullet> new_bullets = new ArrayList<Bullet>();
        for (Bullet bullet: bullets){
            for(Wall wall: map){
                if(Intersector.overlapConvexPolygons(bullet.collision, wall.collision)){
                    if(wall.angle==0){
                        bullet.angle = 360 - bullet.angle;
                    }
                    else{
                        bullet.angle = 180 - bullet.angle;
                        if(bullet.angle<0){
                            bullet.angle += 360;
                        }
                    }
                }
            }

            //bullet movement
            bullet.collision.translate((float)(-300 * Math.sin(Math.toRadians(bullet.angle)) * Gdx.graphics.getDeltaTime()),
                    (float)(300 * Math.cos(Math.toRadians(bullet.angle)) * Gdx.graphics.getDeltaTime()));
            bullet.timer++;

            //bullet lasting
            if(bullet.timer<200&&bullet.collision.getX()<786&&bullet.collision.getX()>5&&bullet.collision.getY()<788&&bullet.collision.getY()>5){
                new_bullets.add(bullet);
            }

        }
        bullets = new_bullets;

        if (dead && !explode){
            exp.play(2.0f);
            explode = true;
        }

        // Keyboard Shoot Bullet
        if(Gdx.input.isKeyPressed(Input.Keys.M) && bullet_cooldown >= 50 && !stop){
            float angle_temp = angle;
            float[] vertices = collision.getTransformedVertices();
            float x_temp = (vertices[4]+vertices[6])/2;
            float y_temp = (vertices[5]+vertices[7])/2;
            Bullet bullet = new Bullet(x_temp, y_temp , angle);
            bullets.add(bullet);
            System.out.println("angle: " + bullet.angle);
            bullet_cooldown = 0;
            fire.play(1.0f);
        }
    }
}
