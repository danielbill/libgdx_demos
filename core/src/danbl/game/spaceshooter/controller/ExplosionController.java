package danbl.game.spaceshooter.controller;
/*
Time : 22/10/19 0:04    
Author : 毕磊              
Site : ---                 
File : ExplosionController.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import danbl.game.spaceshooter.entity.Explosion;
import danbl.game.spaceshooter.entity.Ship;

import java.util.Iterator;
import java.util.LinkedList;

public class ExplosionController {
    private TextureRegion[] explosion1D;
    private Texture explosionImage;
    private Animation<TextureRegion> explosionAni;
    private LinkedList<Explosion> explosions;
    private float animationDuration = 1.2f;
    private Sound boom;

    public ExplosionController() {
        explosionImage = new Texture("spaceshoot/explosion.png");
        TextureRegion[][] images = TextureRegion.split(explosionImage, 64, 64);
        int frames = 16;
        explosion1D = new TextureRegion[frames];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                explosion1D[index++]=images[i][j];
            }
        }
        explosionAni = new Animation<>(animationDuration/ frames, explosion1D);
        explosions = new LinkedList<>();
        boom= Gdx.audio.newSound(Gdx.files.internal("audio/boom1.mp3"));
    }

    public void detectShipDead(Ship ship) {
        if(ship==null) return;
        if(!ship.isDead()) return;
        Explosion expl = new Explosion(this.explosionAni,animationDuration,ship.getBoundingBox());
        this.explosions.add(expl);
        boom.play();
    }

    public void render(Batch batch ,float deltaTime){
        Iterator<Explosion> iter = this.explosions.iterator();
        while(iter.hasNext()){
            Explosion expl = iter.next();
            expl.render(batch,deltaTime);
            if(expl.isAnimationOver()){
                iter.remove();
            }
        }
    }
}
