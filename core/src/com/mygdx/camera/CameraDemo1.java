package com.mygdx.camera;
/*
Time : 22/10/19 17:47    
Author : 毕磊              
Site : ---                 
File : CameraDemo1.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.Random;

public class CameraDemo1 extends ApplicationAdapter {
    static final int WORLD_WIDTH = 200;
    static final int WORLD_HEIGHT = 200;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private Sprite mapSprite;
    private float rotationSpeed;
    private Random r;
    private float shakeCameraFrequency = 3f;
    private float shakeCameraTimer = 0;
    private float shakeCameraDuration = 0.5f;
    private int shakeCameraStrength = 4;

    @Override
    public void create() {
        rotationSpeed = 0.5f; //定义旋转角度
        mapSprite = new Sprite(new Texture(Gdx.files.internal("map/mapdemo.jpg")));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(30, 30 * (h / w)); //相机的视野范围
        cam.position.set(0, 0, 0); //相机的初始位置
        cam.update();
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean scrolled(float amountX, float amountY) {
                //放大相机
                if (amountY<=-1) {
                    cam.zoom += 0.05;
                }
                //缩小相机
                if (amountY>=1) {
                    cam.zoom -= 0.05;
                }
                Gdx.app.log("zoom","cam.zoom is "+cam.zoom);
                cam.update();
                return false;
            }
        });

        r= new Random();

    }

    @Override
    public void render() {
        //渲染
        handleInput();
        resetCamera();
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        mapSprite.draw(batch);
        shockCamera();
        batch.end();
    }


    private void resetCamera() {
        //重置相机位置
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
            cam.zoom = 1;
            cam.rotate(0, 0, 0, 1);
        }
    }

    private void handleInput() {
        //放大相机
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom += 0.02;
        }
        //缩小相机
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }
        //左移相机
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
        }
        //右移相机
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
        }
        //下移相机
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -3, 0);
        }
        //上移相机
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 3, 0);
        }
        //逆时针旋转相机
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.rotate(-rotationSpeed, 0, 0, 1);
        }
        //顺时针旋转相机
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.rotate(rotationSpeed, 0, 0, 1);
        }
        //判定相机范围，不超出地图外
        cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100 / cam.viewportWidth);
        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }

    @Override
    public void resize(int width, int height) {
        //调整窗口，相机位置不变
        cam.viewportWidth = 30f;
        cam.viewportHeight = 30f * height / width;
    }


    @Override
    public void dispose() {
        //释放资源
        mapSprite.getTexture().dispose();
        batch.dispose();

    }

    private void shockCamera(){
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.app.log("shake","delta time is  "+delta);
        shakeCameraTimer+= delta;
        if(shakeCameraTimer>=shakeCameraFrequency) {
            float shaking = 0;
            float x = cam.position.x;
            float y = cam.position.y;
            Gdx.app.log("shake","the cam x and y is "+x+" "+y);
            while(shaking<shakeCameraDuration) {
                shaking+=delta;
                Gdx.app.log("shake","the cam shaked , shocking time is  "+shaking);
                float rf = r.nextFloat();
                int xOffset = r.nextInt(shakeCameraStrength);
                int yOffset = r.nextInt(shakeCameraStrength);
                xOffset = rf > 0.5 ? xOffset : xOffset * -1;
                yOffset = rf > 0.5 ? yOffset : yOffset * -1;
                cam.translate(xOffset, yOffset);
                Gdx.app.log("shake","the cam shaked , offset is "+xOffset+" "+yOffset);
//                cam.update();
            }
            shakeCameraTimer=0;
        }

    }

    public static void main(String[] args) {
        Random r = new Random();
        float f  = r.nextFloat();
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextFloat()>0.5?r.nextInt(11):r.nextInt(11)*-1);
        }

    }
}
