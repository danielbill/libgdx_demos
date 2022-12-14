package danbl.game.spaceshooter.controller;
/*
Time : 22/10/18 6:37    
Author : 毕磊              
Site : ---                 
File : PlayerController.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import danbl.game.core.algo.PlayerFireAlgo;
import danbl.game.spaceshooter.ForceType;
import danbl.game.spaceshooter.GameConstant;
import danbl.game.spaceshooter.entity.Bullet;
import danbl.game.spaceshooter.entity.PlayerShip;
import danbl.game.spaceshooter.view.GameScreen;

public class PlayerController extends BaseController{

    private PlayerShip player;
    private float playerMoveRightLimit;
    private float playerMoveUpLimit;


    public PlayerController(GameScreen gs){
        super(gs);
        TextureRegion ship = gs.ta.findRegion("6B");
        TextureRegion shield = gs.ta.findRegion("shield2");
        TextureRegion bullet = gs.ta.findRegion("laserBlue01");
        Sound laserSound = Gdx.audio.newSound(Gdx.files.internal("audio/laser.mp3"));
        Sound hitSound= Gdx.audio.newSound(Gdx.files.internal("audio/sfx_shieldDown.ogg"));
        Sound deadSound= Gdx.audio.newSound(Gdx.files.internal("audio/dead.mp3"));
        player = new PlayerShip(gs.WORLD_WIDTH / 2,gs.WORLD_HEIGHT * 0.2f,
                40, 8,8,0.5f,5,1,
                ship, shield,
                GameConstant.TOWARDS_UP);
        Bullet playerBullet = new Bullet(50,2,5,bullet,GameConstant.TOWARDS_UP);
        playerBullet.setFireSound(laserSound);
        player.setBullet(playerBullet);
        player.setDeadSound(deadSound);
        player.setHitSound(hitSound);
        player.setForceType(ForceType.FORCE_PLAYER);
        player.setGameCtrl(gs.getGameCtrl());
        this.playerMoveUpLimit = this.upLimit*0.7f-player.getHeight();
        this.playerMoveRightLimit = this.rightLimit-player.getWidth();
        player.setPlayerFireAlgo(new PlayerFireAlgo());


    }

    private void movePlayer(float deltaTime){
        float offset = player.getSpeed()*deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.A) && player.getX()>this.leftLimit){
//            Gdx.app.log("2","key left pressed!");
            player.move(-offset,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && player.getX()<playerMoveRightLimit){
            player.move(offset,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W) && player.getY()<this.playerMoveUpLimit){
            player.move(0,offset);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) && player.getY()>this.downLimit){
            player.move(0,-offset);
        }
    }


    public void render(Batch batch, float deltaTime) {
        if(player==null) return;
        movePlayer(deltaTime);
        if(player.getHp()>0)
            player.render(batch,deltaTime);
        else {
            player = null;
        }

    }



    public PlayerShip getPlayer() {
        return player;
    }
}
