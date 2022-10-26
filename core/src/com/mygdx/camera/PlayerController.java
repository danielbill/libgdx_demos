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
    private float playerMoveLeftLimit;
    private float playerMoveDownLimit;
    private World world;
    private GameScreen gs;


    public PlayerController(GameScreen gs,World world){
        this.gs=gs;
        this.world=world;
        TextureRegion ship = gs.ta.findRegion("8-1");
        player = new Player(ship,500,50,50);
        player.setPosition(world.WORLD_CENTER_X,world.WORLD_CENTER_Y);
//        player.setVisible(false);
        this.playerMoveUpLimit =  world.WORLD_HEIGHT *0.9f- player.getHeight()/2;
        this.playerMoveRightLimit = world.WORLD_WIDTH *0.1f- player.getWidth()/2;
        this.playerMoveLeftLimit =world.WORLD_WIDTH *0.9f;
        this.playerMoveDownLimit = world.WORLD_HEIGHT *0.1f;
        gs.getStage().addActor(player);

    }

    public void movePlayer(float deltaTime){
        float offset = player.getSpeed()*deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.A) && player.getX()>playerMoveRightLimit){
//            Gdx.app.log("2","key left pressed!");
            player.move(-offset,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && player.getX()<playerMoveLeftLimit){
            player.move(offset,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W) && player.getY()<this.playerMoveUpLimit){
            player.move(0,offset);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) && player.getY()>playerMoveDownLimit){
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
