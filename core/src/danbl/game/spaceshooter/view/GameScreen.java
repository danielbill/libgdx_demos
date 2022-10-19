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
import danbl.game.spaceshooter.controller.*;
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
    private InfoBar infoBar;
    private GameController gameCtrl;

    final public int WORLD_WIDTH = 72;
    final public int WORLD_HEIGHT = 128;

    public GameScreen(GameController gameCtrl){
        this.gameCtrl=gameCtrl;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        ta = new TextureAtlas("ss.atlas");
        gbg = new GameBackground(this);
        playerController = new PlayerController(this);
        enemyController = new EnemyController(this);
        bulletController = new BulletController(this);
        explosionController = new ExplosionController();
        infoBar = new InfoBar(this);
    }


    private float checkPause(float delta){
        return gameCtrl.isPaused() ? 0 : delta;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        delta =checkPause(delta);

        enemyController.spawnEnemy(delta);
        //移动背景
        gbg.render(batch, delta);
        bulletController.render(batch,delta);
        playerController.render(batch, delta);
        enemyController.render(batch, delta);
        infoBar.render(batch,delta);

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
        bulletController.detectBulletHitEnemy(enemyController.getEnemyBoss());
        bulletController.getEnemyBullets(enemyController.getEnemyBoss());
        bulletController.detectBulletHitPlayer(playerController.getPlayer());
        explosionController.detectShipDead(playerController.getPlayer());

        explosionController.render(batch,delta);
        infoBar.drawPause(batch);
        batch.end();



    }



    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void pause() {
        batch.begin();
        infoBar.eFont.draw(batch, "PAUSE...", WORLD_WIDTH / 2 - 10, WORLD_HEIGHT / 2);
        batch.end();
//        Gdx.app.log("pause"," iam in pausing");
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

    public InfoBar getInfoBar() {
        return infoBar;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public GameController getGameCtrl() {
        return gameCtrl;
    }
}
