package danbl.game.core;
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
import com.badlogic.gdx.scenes.scene2d.Actor;

public class World extends Actor {
    public static int WORLD_WIDTH = 240;
    public static int WORLD_HEIGHT = 180;
    public static final int WORLD_CENTER_X = WORLD_WIDTH/2;
    public static final int WORLD_CENTER_Y = WORLD_HEIGHT/2;
    private Sprite worldBgMap;

    public World(Sprite worldBgMap) {
        this.worldBgMap = worldBgMap;
        this.worldBgMap.setSize(WORLD_WIDTH,WORLD_HEIGHT);
    }
    public World(Sprite worldBgMap,int worldWidth,int worldHeight) {
        this(worldBgMap);
        WORLD_WIDTH=worldWidth;
        WORLD_HEIGHT=worldHeight;
    }

    public void render(Batch batch,float delta){
        worldBgMap.draw(batch);
    }

    public Sprite getWorldBgMap() {
        return worldBgMap;
    }

}
