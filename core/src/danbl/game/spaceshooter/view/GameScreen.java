package danbl.game.spaceshooter.view;
/*
Time : 22/10/17 12:24    
Author : 毕磊              
Site : ---                 
File : GameScreen.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import danbl.game.spaceshooter.controller.BulletController;
import danbl.game.spaceshooter.controller.EnemyController;
import danbl.game.spaceshooter.controller.ExplosionController;
import danbl.game.spaceshooter.controller.PlayerController;
import danbl.game.spaceshooter.entity.Ship;

public class GameScreen implements Screen {
    // screen part
    private Camera camera;
    private Viewport viewport;
    // graphics
    SpriteBatch batch;
    private GameBackground gbg;
    private EnemyController enemyController;
    private PlayerController playerController;
    public TextureAtlas ta;
    private BulletController bulletController;
    private ExplosionController explosionController;

    final public int WORLD_WIDTH = 72;
    final public int WORLD_HEIGHT = 128;

    public GameScreen(){
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        ta = new TextureAtlas("ss.atlas");
        gbg = new GameBackground(this);
        playerController = new PlayerController(this);
        enemyController = new EnemyController(this);
        bulletController = new BulletController(this);
        explosionController = new ExplosionController();
    }



    @Override
    public void render(float delta) {
        batch.begin();
        enemyController.spawnEnemy(delta);
        //移动背景
        gbg.render(batch, delta);
        playerController.render(batch, delta);
        enemyController.render(batch, delta);
        bulletController.render(batch,delta);
        Ship player = playerController.getPlayer();
        bulletController.getPlayerBullets(player);
        //移动后进行船体间及子弹的碰撞检测
        for(Ship enemy: enemyController.getEnemies()) {
            if(player!=null) {
                player.detectCollideWithEnemy(enemy);
            }
            bulletController.getEnemyBullets(enemy);
            bulletController.detectBulletHitEnemy(enemy);
            explosionController.detectShipDead(enemy);
        }
        bulletController.detectBulletHitPlayer(playerController.getPlayer());
        explosionController.detectShipDead(playerController.getPlayer());

        explosionController.render(batch,delta);

        batch.end();

    }



    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        ta.dispose();

    }

    @Override
    public void show() {

    }
}
