package com.mygdx.camera;
/*
Time : 22/10/20 14:19    
Author : 毕磊              
Site : ---                 
File : Player.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Player extends Actor {
    private TextureRegion body;
    private float speed;
    private ClickListener clickListener;
    private boolean hit=false;

    public Player(TextureRegion body,float speed,int width,int height) {
        this.body = body;
        this.speed=speed;
        setWidth(width);
        setHeight(height);
        setTouchable(Touchable.enabled);
        addListener(clickListener = new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.log("hit","i am hit!!!!");
                playerHit();
            }
        });
    }

    private void playerHit(){
        this.hit = true;
    }

    public void clearStat(){
        this.hit=false;
    }

    public void draw (Batch batch, float parentAlpha) {
        batch.draw(body,this.getX(),this.getY(),getWidth(),getHeight());
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public TextureRegion getBody() {
        return body;
    }

    public void setBody(TextureRegion body) {
        this.body = body;
    }

    public boolean isHit() {
        return hit;
    }

    public void move(float xOffset, float yOffset){
        this.setPosition(this.getX()+xOffset,this.getY()+yOffset);
    }
}
