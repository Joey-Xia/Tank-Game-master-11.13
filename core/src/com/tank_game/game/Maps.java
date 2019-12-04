package com.tank_game.game;

import java.util.ArrayList;

public class Maps {
    public ArrayList<Wall> map1;
    public ArrayList<Wall> map2;
    public ArrayList<Wall> map3;

    public Maps(){
        map1 = new ArrayList<Wall>();
        map2 = new ArrayList<Wall>();
        map3 = new ArrayList<Wall>();
        /*
        map1.add(new Wall(100,100,0));
        map1.add(new Wall(200,100,0));
        map1.add(new Wall(300,100,0));
        map1.add(new Wall(400,100,0));

         */


        //lower bound
        map2.add(new Wall(395,-395,90, 800, 10));
        //upper bound
        map2.add(new Wall(395,395,90, 800, 10));
        //left bound
        map2.add(new Wall(0,0,0, 800, 10));
        //right bound
        map2.add(new Wall(790,0,0, 800, 10));

        //inner walls
        map2.add(new Wall(570,75,90, 250, 10));
        map2.add(new Wall(570,275,90, 250, 10));
        map2.add(new Wall(570,475,90, 250, 10));

        map2.add(new Wall(220,75,90, 250, 10));
        map2.add(new Wall(220,275,90, 250, 10));
        map2.add(new Wall(220,475,90, 250, 10));

        float x = 0;
        float y = 0;
        x = map2.get(4).collision.getTransformedVertices()[4]-1;
        y = map2.get(4).collision.getTransformedVertices()[7];
        map2.add(new Wall(x,y,0, 10, 1));
        map2.add(new Wall(x+252,y,0, 10, 1));

        x = map2.get(5).collision.getTransformedVertices()[4]-1;
        y = map2.get(5).collision.getTransformedVertices()[7];
        map2.add(new Wall(x,y,0, 10, 1));
        map2.add(new Wall(x+252,y,0, 10, 1));

        x = map2.get(6).collision.getTransformedVertices()[4]-1;
        y = map2.get(6).collision.getTransformedVertices()[7];
        map2.add(new Wall(x,y,0, 10, 1));
        map2.add(new Wall(x+252,y,0, 10, 1));

        x = map2.get(7).collision.getTransformedVertices()[4]-1;
        y = map2.get(7).collision.getTransformedVertices()[7];
        map2.add(new Wall(x,y,0, 10, 1));
        map2.add(new Wall(x+252,y,0, 10, 1));

        x = map2.get(8).collision.getTransformedVertices()[4]-1;
        y = map2.get(8).collision.getTransformedVertices()[7];
        map2.add(new Wall(x,y,0, 10, 1));
        map2.add(new Wall(x+252,y,0, 10, 1));

        x = map2.get(9).collision.getTransformedVertices()[4]-1;
        y = map2.get(9).collision.getTransformedVertices()[7];
        map2.add(new Wall(x,y,0, 10, 1));
        map2.add(new Wall(x+252,y,0, 10, 1));


        /*
        map2.add(new Wall(220,200,90));
        map2.add(new Wall(300,400,90));
        map2.add(new Wall(220,400,90));
        map2.add(new Wall(300,600,90));
        map2.add(new Wall(220,600,90));

         */
        /*
        map3.add(new Wall(100,100,45));
        map3.add(new Wall(200,100,45));
        map3.add(new Wall(300,100,45));
        map3.add(new Wall(400,100,45));

         */
    }

    public ArrayList<Wall> getMap(int map_num) {
        if(map_num==1){
            return map1;
        }
        if(map_num==2){
            return map2;
        }
        if(map_num==3){
            return map3;
        }
        System.out.println("non existing map");
        return null;
    }
}
