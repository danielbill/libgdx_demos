package danbl.game.spaceshooter.entity;
/*
Time : 22/10/17 18:45    
Author : 毕磊              
Site : ---                 
File : Ship.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship  extends Shooter{
    private int shieldHP;
    private int hp;
    private TextureRegion shipTr;
    private TextureRegion shieldTr;


    public Ship(float x, float y, float speed, int width, int height,
                float fireFrequency, int shieldHP, int hp,
                TextureRegion shipTr, TextureRegion shieldTr,
                int towards) {
        super(x, y, speed, width, height, fireFrequency,towards);
        this.shieldHP = shieldHP;
        this.hp = hp;
        this.shipTr = shipTr;
        this.shieldTr = shieldTr;
    }

    public void update(float deltaTime){
        updateLastShotTime(deltaTime);
    }

    public void draw(Batch batch){
        batch.draw(shipTr,x,y,width,height);
        if(shieldHP>0){
            batch.draw(shieldTr, x-1, y+0.2f*height*towards, width+2, height);
        }
    }

    public void collided(){
        this.shieldHP--;
        if(this.shieldHP<0){
            this.shieldHP=0;
            this.hp--;
        }
    }

    public int getHp() {
        return hp;
    }
    public boolean isDead(){
        return hp<=0;
    }
}
