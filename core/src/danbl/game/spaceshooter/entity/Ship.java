package danbl.game.spaceshooter.entity;
/*
Time : 22/10/17 18:45    
Author : 毕磊              
Site : ---                 
File : Ship.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import danbl.game.core.misc.MyShapeDrawer;

public class Ship  extends Shooter{
    protected int shieldHP;
    protected int hp;
    protected TextureRegion shipTr;
    private TextureRegion shieldTr;
    protected boolean showHp;
    protected Rectangle blood;
    public MyShapeDrawer drawer;
    protected int maxHp;


    public Ship(float x, float y, float speed, int width, int height,
                float fireFrequency, int shieldHP, int hp,
                TextureRegion shipTr, TextureRegion shieldTr,
                int towards) {
        super(x, y, speed, width, height, fireFrequency,towards);
        this.shieldHP = shieldHP;
        this.hp = hp;
        this.shipTr = shipTr;
        this.shieldTr = shieldTr;
        this.maxHp=hp;
        blood = new Rectangle();
        blood.set(x+2,y+height+2,width-2,2);
        showHp=false;
    }

    public void update(float deltaTime){
        updateLastShotTime(deltaTime);
    }

    public void draw(Batch batch){
        float bloodRemain = (float)hp/maxHp;
        blood.set(x+2,y+height+1,width*bloodRemain-2,1);
        batch.draw(shipTr,x,y,width,height);
        if(shieldHP>0){
            batch.draw(shieldTr, x-1, y+0.2f*height*towards, width+2, height);
        }
        if(showHp && hp>0){
            drawer.setColor(Color.RED);
            drawer.drawer.filledRectangle(blood);
            Gdx.app.log("draw shape","the shape is "+blood+" blood width is "+width*bloodRemain);
        }
    }

    public void collided(){
        this.shieldHP--;
        if(this.shieldHP<0){
            this.shieldHP=0;
            this.hp--;
        }
        playCollided();
    }

    protected void playCollided(){

    }

    public int getHp() {
        return hp;
    }
    public boolean isDead(){
        return hp<=0;
    }

    public int getShieldHP() {
        return shieldHP;
    }

    public void setShowHp(boolean showHp) {
        this.showHp = showHp;
    }

    public static void main(String[] args) {
        System.out.println(50/50f);
        System.out.println(49/50f);
    }
}
