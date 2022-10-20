package danbl.game.spaceshooter.entity;
/*
Time : 22/10/18 8:08    
Author : 毕磊              
Site : ---                 
File : Shooter.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.audio.Sound;
import danbl.game.core.algo.PlayerFireAlgo;
import danbl.game.core.entity.MoveableForce;

public abstract class Shooter extends MoveableForce {
    protected float timeBetweenShots;
    protected float timeSinceLastShot=0;
    protected Bullet bullet;
    private PlayerFireAlgo playerFireAlgo;

    public void setBullet(Bullet bullet){
        this.bullet=bullet;
    }


    public Shooter(float x, float y, float speed, int width, int height,
                   float timeBetweenShots,int towards) {
        super(x, y, speed, width, height,towards);
        this.timeBetweenShots = timeBetweenShots;
    }

    protected void updateLastShotTime(float deltaTime){
        this.timeSinceLastShot += deltaTime;
    }

    public boolean canFireBullet(){
        boolean timeIsOk = this.timeSinceLastShot>=this.timeBetweenShots;
        if(this.playerFireAlgo!=null){
            return timeIsOk&this.playerFireAlgo.isFiring();
        }
        return timeIsOk;
    }

    public Bullet fireBullet(){
        towards = this.getBullet().getTowards();
        Bullet bullet = new Bullet(this.x+this.width/2-this.getBullet().getWidth()/2,
                this.y+this.bullet.getHeight()*towards,
                this.bullet.getSpeed(),this.bullet.getWidth(),this.bullet.getHeight(),
                this.bullet.body,towards);
        this.timeSinceLastShot=0;
        bullet.setForceType(this.forceType);
        Sound fireSound = this.bullet.getFireSound();
//        Gdx.app.log("5","this.bullet.getFireSound() = "+this.getBullet().getFireSound());
        if(fireSound!=null){
            fireSound.play();
        }

        return bullet;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setPlayerFireAlgo(PlayerFireAlgo playerFireAlgo) {
        this.playerFireAlgo = playerFireAlgo;
    }
}
