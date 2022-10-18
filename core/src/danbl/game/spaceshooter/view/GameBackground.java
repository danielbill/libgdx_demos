package danbl.game.spaceshooter.view;
/*
Time : 22/10/17 15:54    
Author : 毕磊              
Site : ---                 
File : GameBackground.java          
Project: libgdx_test   
说明: 游戏背景组织类
*/

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class GameBackground {

    private String[] bgNames = {"bg02","bg05","bg06"};
    private Map<String, TextureRegion> backgrounds=new HashMap<>();
    private float[] bgScrollingSpeeds = {1.5f,2.5f,12};
    private float bgMinScrollingSpeed = 0;
    private float[] bgYoffset={0,0,0};
    private GameScreen gameScreen;

    public GameBackground(GameScreen gameScreen){
        this.gameScreen=gameScreen;
        bgMinScrollingSpeed = (float) gameScreen.WORLD_HEIGHT/100;
        for (int i = 0; i < bgNames.length; i++) {
            String bgName = bgNames[i];
            TextureRegion bg = this.gameScreen.ta.findRegion(bgName);
//            System.out.println(bg);
            backgrounds.put(bgName, bg);
        }
    }
    public void render(SpriteBatch batch, float deltaTime){
        for (int i = 0; i < bgNames.length; i++) {
            String bgName = bgNames[i];
            TextureRegion bg = backgrounds.get(bgName);
            float bgSpeed =  this.bgScrollingSpeeds[i];
            bgYoffset[i]+=deltaTime*bgSpeed*this.bgMinScrollingSpeed;
            if(bgYoffset[i]>gameScreen.WORLD_HEIGHT){
                bgYoffset[i]=0;
            }
            batch.draw(bg,0,-bgYoffset[i],gameScreen.WORLD_WIDTH,gameScreen.WORLD_HEIGHT);
            batch.draw(bg,0,-bgYoffset[i]+gameScreen.WORLD_HEIGHT,
                    gameScreen.WORLD_WIDTH,gameScreen.WORLD_HEIGHT);
        }

    }


}
