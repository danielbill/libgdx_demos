package danbl.game.spaceshooter.view;
/*
Time : 22/10/17 12:24    
Author : 毕磊              
Site : ---                 
File : GameScreen.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import danbl.game.core.effect.ScreenShaking;
import danbl.game.spaceshooter.controller.*;
import danbl.game.spaceshooter.entity.PlayerShip;
import danbl.game.spaceshooter.entity.Ship;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class GameScreen implements Screen {
    final public int WORLD_WIDTH = 72;
    final public int WORLD_HEIGHT = 128;
    public TextureAtlas ta;
    public Stage stage;
    // graphics
    SpriteBatch batch;
    // screen part
    private final Camera camera;
    private final Viewport viewport;
    private GameBackground gbg;
    private EnemyController enemyController;
    private PlayerController playerController;
    private BulletController bulletController;
    private ExplosionController explosionController;
    private ScreenShaking screenShaking;
    private InfoBar infoBar;
    private GameController gameCtrl;
    public ShapeDrawer drawer;

    public GameScreen(GameController gameCtrl) {

        this.gameCtrl = gameCtrl;
        batch = new SpriteBatch();
        initDrawer();
        camera = new OrthographicCamera(); //相机的视野范围
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        stage = new Stage(viewport, batch);

        ta = new TextureAtlas("ss.atlas");
        gbg = new GameBackground(this);
        playerController = new PlayerController(this);
        enemyController = new EnemyController(this);
        bulletController = new BulletController(this);
        explosionController = new ExplosionController();
        infoBar = new InfoBar(this);

    }

    private void initDrawer(){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.CLEAR);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap); //remember to dispose of later
        TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);
        drawer = new ShapeDrawer(batch,region);
        pixmap.dispose();
        texture.dispose();
        Gdx.app.log("drawer","gs.drawer is "+drawer);
    }


    private float checkPause(float delta) {
        return gameCtrl.isPaused() ? 0 : delta;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.begin();
        delta = checkPause(delta);
        shakeScreen(batch, delta);

        enemyController.spawnEnemy(delta);
        //移动背景
        gbg.render(batch, delta);
        bulletController.render(batch, delta);
        playerController.render(batch, delta);
        enemyController.render(batch, delta);
        infoBar.render(batch, delta);
        PlayerShip player = playerController.getPlayer();
        bulletController.getPlayerBullets(player);
        //移动后进行船体间及子弹的碰撞检测
        for (Ship enemy : enemyController.getEnemies()) {
            if (player != null) {
                player.detectCollideWithEnemy(enemy);
            }
            bulletController.getEnemyBullets(enemy);
            bulletController.detectBulletHitEnemy(enemy);
            explosionController.detectShipDead(enemy);
        }
        bulletController.detectBulletHitEnemy(enemyController.getEnemyBoss());
        bulletController.getEnemyBullets(enemyController.getEnemyBoss());
        bulletController.detectBulletHitPlayer(player);
        explosionController.detectShipDead(player);

        explosionController.render(batch, delta);
        infoBar.drawPause(batch);

        batch.end();
        stage.act();
        stage.draw();

    }

    private void shakeScreen(Batch batch, float delta) {
        if (delta==0) return;
        if (screenShaking == null) return;
        Gdx.app.log("shake test","in shakeScreen method");
        screenShaking.shakeScreen(delta);
        if(!screenShaking.isShaking()){
            this.screenShaking=null;
        }
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

    public void setScreenShaking(ScreenShaking screenShaking) {
        this.screenShaking = screenShaking;
    }

    public Camera getCamera() {
        return camera;
    }
    public void shakeCamera(){
        screenShaking = new ScreenShaking(camera,0.7f,2,false);

    }
}
