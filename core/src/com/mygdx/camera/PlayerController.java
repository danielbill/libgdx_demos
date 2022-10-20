package com.mygdx.camera;
/*
Time : 22/10/20 11:48    
Author : 毕磊              
Site : ---                 
File : PlayerController.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import danbl.game.core.World;

public class PlayerController  {

    private Player player;
    private float playerMoveRightLimit;
    private float playerMoveUpLimit;
    private World world;
    private GameScreen gs;


    public PlayerController(GameScreen gs,World world){
        this.gs=gs;
        this.world=world;
        TextureRegion ship = gs.ta.findRegion("8-1");
        player = new Player(ship,250,55,55);
        player.setPosition(world.WORLD_CENTER_X,world.WORLD_CENTER_Y);
//        player.setVisible(false);
        this.playerMoveUpLimit =  world.WORLD_HEIGHT -player.getHeight();
        this.playerMoveRightLimit = world.WORLD_WIDTH -player.getWidth();
        gs.getStage().addActor(player);

    }

    public void movePlayer(float deltaTime){
        float offset = player.getSpeed()*deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.A) && player.getX()>0){
//            Gdx.app.log("2","key left pressed!");
            player.move(-offset,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && player.getX()<playerMoveRightLimit){
            player.move(offset,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W) && player.getY()<this.playerMoveUpLimit){
            player.move(0,offset);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) && player.getY()>0){
            player.move(0,-offset);
        }
    }


    public void checkPlayerStat(){
        if(player.isHit()){
            player.clearStat();
            gs.shakeCamera();
        }
    }

    public Player getPlayer() {
        return player;
    }

}
