package com.mygdx.camera;
/*
Time : 22/10/20 7:06    
Author : 毕磊              
Site : ---                 
File : GameScreen.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import danbl.game.core.World;
import danbl.game.core.effect.ScreenShaking;

/**
 * 没有解决镜头相对背景居中的问题
 */
public class GameScreen implements Screen {
    public CameraShakeDemo game;
    public World world;
    public Stage stage;
    public TextureAtlas ta;
    private final Camera camera;
    private final Viewport viewport;
    private final SpriteBatch batch;
    private Sprite bgSprite;
    private Skin skin;
    private PlayerController playerController;
    private ScreenShaking screenShaking;

    public GameScreen(CameraShakeDemo game, World world) {
        this.game = game;
//        game.multiplexer.addProcessor(this);
        this.world = world;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
//        viewport = new FitViewport(world.WORLD_WIDTH/2, world.WORLD_HEIGHT/2,camera);
//        viewport.setScreenPosition(world.WORLD_CENTER_X,world.WORLD_CENTER_Y);

        initStage();
        ta = new TextureAtlas("ss.atlas");
        playerController = new PlayerController(this, world);
    }

    private void initStage() {
        skin = new Skin(Gdx.files.internal("skin/metalui/metal-ui.json"));
        stage = new Stage(viewport, batch);
//        stage.addActor(world);
        game.multiplexer.addProcessor(stage);
        TextButton tb = new TextButton("try", skin);
        tb.setX(world.WORLD_CENTER_X);
        tb.setY(world.WORLD_CENTER_Y);
        stage.addActor(tb);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        cameraFollowPlayer(playerController.getPlayer());
        camera.update();
//        Gdx.app.log("camera","now camera position is "+camera.position);
        batch.begin();
        world.render(batch, delta);
        playerController.movePlayer(delta);
        playerController.checkPlayerStat();
        shakeScreen(batch,delta);

        batch.end();
        stage.act();
        stage.draw();
    }

    private void cameraFollowPlayer(Player player) {
        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
        Gdx.app.log("screen","viewpost screenWidth:"+viewport.getScreenWidth());
    }

    private void shakeScreen(Batch batch, float delta) {
        if (screenShaking == null) return;
        Gdx.app.log("shake test","in shakeScreen method");
        screenShaking.shakeScreen(delta);
        if(!screenShaking.isShaking()){
            this.screenShaking=null;
        }
    }




    @Override
    public void dispose() {
        batch.dispose();
        bgSprite.getTexture().dispose();
        skin.dispose();
        stage.dispose();
        ta.dispose();
    }


    public Camera getCamera() {
        return camera;
    }

    public Stage getStage() {
        return stage;
    }

    public void shakeCamera(){
        screenShaking = new ScreenShaking(camera,1f,10,false);

    }

    @Override
    public void show() {

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
}
