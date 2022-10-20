package danbl.game.spaceshooter.controller;
/*
Time : 22/10/18 6:47    
Author : 毕磊              
Site : ---                 
File : EnemyController.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import danbl.game.core.ai.MovementAI;
import danbl.game.spaceshooter.ForceType;
import danbl.game.spaceshooter.GameConstant;
import danbl.game.spaceshooter.entity.Bullet;
import danbl.game.spaceshooter.entity.Ship;
import danbl.game.spaceshooter.view.GameScreen;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class EnemyController extends BaseController{
    private LinkedList<Ship> enemys=new LinkedList<>();
    private final float edgePadding = 10;
    private float spawnEnemyFrequency=3f;
    private float spawnTimeGap=0;
    private TextureRegion[] shipTrs;
    private TextureRegion[] bulletTrs;
    private TextureRegion shieldTr;
    private TextureRegion enemyBossTr;
    private Ship enemyBoss;


    public EnemyController(GameScreen gs){
        super(gs);
        shipTrs = new TextureRegion[5];
        bulletTrs = new TextureRegion[2];
        shipTrs[0] =gs.ta.findRegion("8-1");

        shieldTr = gs.ta.findRegion("shield1");

        bulletTrs[0] = gs.ta.findRegion("laserRed04");
        bulletTrs[1] = gs.ta.findRegion("laserRed09");

        shieldTr.flip(false,true);

        enemyBossTr = gs.ta.findRegion("13");
        initBoss();
        genEnemyRandomly();
    }

    public void spawnEnemy(float deltaTime){
        spawnTimeGap+=deltaTime;
        if(spawnTimeGap>=spawnEnemyFrequency){
            spawnTimeGap=0;
            genEnemyRandomly();
        }
    }

    private void initBoss(){
        int width = 35;
        int height =25;
        enemyBoss = new Ship(gs.WORLD_WIDTH/2-width/2,gs.WORLD_HEIGHT-height,
                10,width,height,3,
                0,1000,enemyBossTr,shieldTr, GameConstant.TOWARDS_DOWN);
        MovementAI moveAI = new MovementAI(gs,2f);
        moveAI.setDownLimit(gs.WORLD_HEIGHT/2);
        moveAI.bindMoveableObject(enemyBoss);
        TextureRegion bulletTr = gs.ta.findRegion("laserRed08");
        Bullet enemyBullet = new Bullet(80,10,10,bulletTr,GameConstant.TOWARDS_DOWN);
        Sound bulletSound = Gdx.audio.newSound(Gdx.files.internal("audio/大炮.mp3"));
        enemyBullet.setFireSound(bulletSound);
        enemyBoss.setBullet(enemyBullet);
        enemyBoss.setForceType(ForceType.FORCE_ENEMY);
        enemyBoss.setShowHp(true);
        enemyBoss.drawer=this.gs.drawer;
        Gdx.app.log("drawer","this.gs.drawer is "+this.gs.drawer);
    }

    private void genEnemyRandomly(){
        Random r = GameConstant.r;//new Random();
        int rSize = r.nextInt(5,8);
        int rSpeed = r.nextInt(15,40);
        float rX = this.enemyBoss.getX()+enemyBoss.getWidth()/2;
        float rY = enemyBoss.getY()-rSize;
        TextureRegion shipTr = shipTrs[0];
        int shieldHP = r.nextInt(0,2);
        Ship enemy = new Ship(rX,rY,rSpeed,rSize,rSize,r.nextFloat(0.75f,1.2f),
                shieldHP,1,shipTr,shieldTr, GameConstant.TOWARDS_DOWN);
        TextureRegion bulletTr = bulletTrs[0];
        Bullet enemyBullet = new Bullet(50,2,3,bulletTr,GameConstant.TOWARDS_DOWN);
        enemy.setBullet(enemyBullet);
        enemy.setForceType(ForceType.FORCE_ENEMY);
        MovementAI moveAI = new MovementAI(gs,r.nextFloat(0.5f,1.5f));
        moveAI.bindMoveableObject(enemy);
        enemy.drawer=gs.drawer;
        enemys.add(enemy);
    }



    public void render(Batch batch, float deltaTime) {
        if(enemyBoss!=null){
            if(!enemyBoss.isDead()){
                enemyBoss.moveByAI(deltaTime);
                enemyBoss.render(batch,deltaTime);
            }else{
                enemyBoss=null;
            }
        }
        Iterator<Ship> iter = enemys.iterator();
        while(iter.hasNext()){
            Ship enemy = iter.next();
            if(enemy.getHp()<=0) {
                iter.remove();
                gs.getInfoBar().addScore();
                continue;
            }
            enemy.moveByAI(deltaTime);
            enemy.render(batch, deltaTime);
        }
    }

    public LinkedList<Ship> getEnemies() {
        return enemys;
    }

    public Ship getEnemyBoss() {
        return enemyBoss;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            System.out.println(GameConstant.r.nextFloat(0.6f,1.5f));
        }
    }


}
