package danbl.game.spaceshooter.entity;
/*
Time : 22/10/18 7:51    
Author : 毕磊              
Site : ---                 
File : Bullet.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bullet extends MoveableForce{
    TextureRegion body;
    private Sound fireSound;

    public Bullet(float speed, int width, int height,
                  TextureRegion body,int towards) {
        super(speed, width, height,towards);
        this.body = body;
    }

    public Bullet(float x, float y, float speed, int width, int height,
                  TextureRegion body, int towards) {
        super(x, y, speed, width, height,towards);
        this.body = body;
    }

    @Override
    public void update(float deltaTime) {
        float yOffset = this.towards*deltaTime*this.speed;
        this.y += yOffset;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(body,x,y,width,height);
    }

    public Sound getFireSound() {
        return fireSound;
    }

    public void setFireSound(Sound fireSound) {
        this.fireSound = fireSound;
    }

    @Override
    public void collided() {

    }
}
