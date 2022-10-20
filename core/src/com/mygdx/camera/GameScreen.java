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
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import danbl.game.spaceshooter.effect.ScreenShaking;

public class GameScreen implements Screen, InputProcessor {
    public CameraDemo2 game;
    public World world;
    public Stage stage;
    public TextureAtlas ta;
    private final Camera camera;
    private final Viewport viewport;
    private final SpriteBatch batch;
    private Sprite bgSprite;
    private Skin skin;
    private final PlayerController playerController;
    private ScreenShaking screenShaking;

    public GameScreen(CameraDemo2 game, World world) {
        this.game = game;
        game.multiplexer.addProcessor(this);
        this.world = world;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        initStage();
        ta = new TextureAtlas("ss.atlas");
        playerController = new PlayerController(this, world);
    }

    private void initStage() {
        skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));
        stage = new Stage(viewport, batch);
        game.multiplexer.addProcessor(stage);
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        int gW = Gdx.graphics.getWidth();
        int gH = Gdx.graphics.getHeight();
        TextButton textButton = new TextButton("graphics.getWidth=" + gW, skin);
        root.add(textButton);

        textButton = new TextButton("graphics.getHeight=" + gH, skin);
        root.add(textButton);

    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
//        cameraFollowPlayer(playerController.getPlayer());
//        camera.update();
        batch.begin();
        world.render(batch, delta);
        playerController.movePlayer(delta);
        playerController.checkPlayerStat();
        shakeScreen(batch, delta);
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
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
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
        bgSprite.getTexture().dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public Camera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Stage getStage() {
        return stage;
    }

    public void shakeCamera(){
        screenShaking = new ScreenShaking(camera,2.5f,10,false);

    }
}
