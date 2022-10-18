package danbl.game.spaceshooter.controller;
/*
Time : 22/10/18 6:47    
Author : 毕磊              
Site : ---                 
File : EnemyController.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import danbl.game.spaceshooter.GameConstant;
import danbl.game.spaceshooter.ai.MovementAI;
import danbl.game.spaceshooter.view.GameScreen;
import danbl.game.spaceshooter.entity.Bullet;
import danbl.game.spaceshooter.ForceType;
import danbl.game.spaceshooter.entity.Ship;

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
    private TextureRegion shipTr;
    private TextureRegion shieldTr;
    private TextureRegion bulletTr;

    public EnemyController(GameScreen gs){
        super(gs);
        shipTrs = new TextureRegion[5];
        bulletTrs = new TextureRegion[2];
        shipTrs[0] =gs.ta.findRegion("enemy1");
        shipTrs[1] =gs.ta.findRegion("enemy2");
        shipTrs[2] =gs.ta.findRegion("enemy3");
        shipTrs[3] =gs.ta.findRegion("enemy4");
        shipTrs[4] =gs.ta.findRegion("enemy5");

        shipTr = gs.ta.findRegion("enemy1");
        shieldTr = gs.ta.findRegion("shield1");
        bulletTr = gs.ta.findRegion("laserGreen");
        bulletTrs[0] = gs.ta.findRegion("laserGreen");
        bulletTrs[1] = gs.ta.findRegion("fire00");

        shieldTr.flip(false,true);
        genEnemyRandomly();
    }

    public void spawnEnemy(float deltaTime){
        spawnTimeGap+=deltaTime;
        if(spawnTimeGap>=spawnEnemyFrequency){
            spawnTimeGap=0;
            genEnemyRandomly();
        }
    }

    private void genEnemyRandomly(){
        Random r = GameConstant.r;//new Random();
        int rSize = r.nextInt(5,7);
        int rSpeed = r.nextInt(15,40);
        float rX = r.nextFloat(rSize+edgePadding,gs.WORLD_WIDTH-rSize-edgePadding);
        float rY = r.nextFloat(gs.WORLD_HEIGHT*0.9f,gs.WORLD_HEIGHT*0.95f);

        Ship enemy = new Ship(rX,rY,rSpeed,rSize,rSize,r.nextFloat(0.4f,1.5f),
                1,1,shipTr,shieldTr, GameConstant.TOWARDS_DOWN);
        Bullet enemyBullet = new Bullet(45,2,3,bulletTr,GameConstant.TOWARDS_DOWN);
        enemy.setBullet(enemyBullet);
        enemy.setForceType(ForceType.FORCE_ENEMY);
        MovementAI moveAI = new MovementAI(gs,r.nextFloat(0.5f,1.5f));
        moveAI.bindMoveableObject(enemy);
        enemys.add(enemy);
    }



    public void render(Batch batch, float deltaTime) {
        Iterator<Ship> iter = enemys.iterator();
        while(iter.hasNext()){
            Ship enemy = iter.next();
            if(enemy.getHp()<=0) {
                iter.remove();
                continue;
            }
            enemy.moveByAI(deltaTime);
            enemy.render(batch, deltaTime);
        }
    }

    public LinkedList<Ship> getEnemies() {
        return enemys;
    }


}
