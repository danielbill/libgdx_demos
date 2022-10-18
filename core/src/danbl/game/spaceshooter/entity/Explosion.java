package danbl.game.spaceshooter.entity;
/*
Time : 22/10/18 23:48    
Author : 毕磊              
Site : ---                 
File : Explosion.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Explosion {
    private Animation<TextureRegion> explosionAnimation;
    private float timer;
    private Rectangle boundingBox;
    private float animationDuration;

    public Explosion(Animation<TextureRegion> explosionAnimation, float animationDuration, Rectangle boundingBox) {
        this.explosionAnimation = explosionAnimation;
        this.animationDuration = animationDuration;
        this.boundingBox = boundingBox;
        timer=0;
    }

    public void render(Batch batch,float deltaTime){
        timer+=deltaTime;
        batch.draw(this.explosionAnimation.getKeyFrame(timer),
                this.boundingBox.x,boundingBox.y,
                boundingBox.width,boundingBox.height);
    }
    public boolean isAnimationOver(){
        return this.explosionAnimation.isAnimationFinished(timer);
    }
}
