package com.mygdx.camera;
/*
Time : 22/10/20 7:22    
Author : 毕磊              
Site : ---                 
File : World.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class World {
    public static final int WORLD_WIDTH = 800;
    public static final int WORLD_HEIGHT = 600;
    public static final int WORLD_CENTER_X = WORLD_WIDTH/2;
    public static final int WORLD_CENTER_Y = WORLD_HEIGHT/2;
    private Sprite worldBgMap;

    public World(Sprite worldBgMap) {
        this.worldBgMap = worldBgMap;
        this.worldBgMap.setSize(WORLD_WIDTH,WORLD_HEIGHT);
    }

    public void render(Batch batch,float delta){
        worldBgMap.draw(batch);
    }
}
