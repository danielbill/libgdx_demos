package com.mygdx.ray3k;
/*
Time : 22/10/16 6:32    
Author : 毕磊              
Site : ---                 
File : Core.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import danbl.game.core.effect.ScreenShaking;

import java.util.HashMap;
import java.util.Map;

public class StageSwitcher extends ApplicationAdapter implements InputProcessor {
    private Skin skin;
    private Map<String,Stage> stages=new HashMap<>();
    private String currentStage = "stage1";
    private Camera camera;
    private ScreenShaking screenShaking;
    private SpriteBatch batch;
    private Viewport viewport;

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
        if(button == Input.Buttons.LEFT) {
            if(currentStage.equals("stage1")){
                currentStage="stage2";
            }else{
                currentStage="stage1";
            }
            return true;
        }

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

    private void createStage1(){
        Stage stage = new Stage(new ScreenViewport());
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        Label tf = new Label("lable1", skin);
//        tf.setSize(100,100);

        root.add(tf).padLeft(20).height(30).left();

        tf = new Label("  lable2", skin);
        root.add(tf).left().padLeft(20);

        tf = new Label("  lable3", skin);
        root.add(tf).left().padLeft(20);

        root.row();
        root.defaults().grow();
        TextButton textButton = new TextButton("Hello", skin);
        root.add(textButton);

        textButton = new TextButton("Hello", skin);
        root.add(textButton);

        textButton = new TextButton("shake!!!!", skin);
        textButton.addListener((new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenShaking = new ScreenShaking(camera,1f,5);
            }
        }));




        root.add(textButton);

        stages.put("stage1",stage);

    }
    private void createStage2(){
        Stage stage = new Stage(new ScreenViewport());
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        Label tf = new Label("this is stage2!!!!", skin);
        root.add(tf).grow();
        stages.put("stage2",stage);
    }

    private void createStages(){
        createStage1();
        createStage2();

    }

    @Override
    public void create() {
        //加载皮肤
        skin  = new Skin(Gdx.files.internal("metalui/metal-ui.json"));
        batch = new SpriteBatch();
        //创建场景
        createStages();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(100, 100, camera);
        //创建输入接收器
        InputMultiplexer multiplexer = new InputMultiplexer(); // 多输入接收器
        Gdx.input.setInputProcessor(multiplexer);
//        multiplexer.addProcessor(this); // 添加手势识别
        stages.forEach((key,stage)->{
            multiplexer.addProcessor(stage);
        });
    }



    @Override
    public void render() {
        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        Stage stage = stages.get(currentStage);
        stage.act();
        stage.draw();
        batch.begin();
        shakeScreen(batch, Gdx.graphics.getDeltaTime());
        batch.end();

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
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        skin.dispose();
        stages.forEach((key,stage)->{
            stage.dispose();
        });
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }
}
