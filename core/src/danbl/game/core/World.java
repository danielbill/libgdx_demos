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
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class World {
    public static int WORLD_WIDTH = 16000;
    public static int WORLD_HEIGHT = 9000;
    public static final int WORLD_CENTER_X = WORLD_WIDTH/2;
    public static final int WORLD_CENTER_Y = WORLD_HEIGHT/2;
    private Sprite worldBgMap;
    private TextureRegion bgMapTR;

    public World(Sprite worldBgMap) {
        this.worldBgMap = worldBgMap;
        this.worldBgMap.setSize(WORLD_WIDTH,WORLD_HEIGHT);
    }
    public World(Sprite worldBgMap,int worldWidth,int worldHeight) {
        this(worldBgMap);
        WORLD_WIDTH=worldWidth;
        WORLD_HEIGHT=worldHeight;
    }

    public World(TextureRegion worldBgMap,int worldWidth,int worldHeight ){
        this.bgMapTR=worldBgMap;
        WORLD_WIDTH=worldWidth;
        WORLD_HEIGHT=worldHeight;

    }

    public void render(Batch batch,float delta){
        if (worldBgMap != null) {
            worldBgMap.draw(batch);
        }else{
            batch.draw(this.bgMapTR,0,0,WORLD_WIDTH,WORLD_HEIGHT);
        }

    }

    public Sprite getWorldBgMap() {
        return worldBgMap;
    }

}
