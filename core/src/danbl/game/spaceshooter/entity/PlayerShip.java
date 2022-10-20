package danbl.game.spaceshooter.entity;
/*
Time : 22/10/20 23:13    
Author : 毕磊              
Site : ---                 
File : PlayerShip.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import danbl.game.spaceshooter.controller.GameController;

public class PlayerShip extends Ship{

    private Sound hitSound;
    private Sound deadSound;

    private GameController gameCtrl;

    public PlayerShip(float x, float y, float speed, int width, int height, float fireFrequency, int shieldHP, int hp, TextureRegion shipTr, TextureRegion shieldTr, int towards) {
        super(x, y, speed, width, height, fireFrequency, shieldHP, hp, shipTr, shieldTr, towards);
    }

    public void setHitSound(Sound hitSound) {
        this.hitSound = hitSound;
    }

    public void setDeadSound(Sound deadSound) {
        this.deadSound = deadSound;
    }

    public void setGameCtrl(GameController gameCtrl) {
        this.gameCtrl = gameCtrl;
    }

    public void playHit(){
        if(hp > 0)
            hitSound.play();
        else {
            gameCtrl.stopMusic();
            deadSound.play();
        }
    }
}
