package com.mygdx.camera;
/*
Time : 22/10/19 16:59    
Author : 毕磊              
Site : ---                 
File : CameraDemo.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class CameraDemo extends ApplicationAdapter implements InputProcessor {
    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private Sprite mapSprite;
    private float rotationSpeed;

    @Override
    public void create() {
        rotationSpeed = 1f; //定义旋转角度
        mapSprite = new Sprite(new Texture(Gdx.files.internal("map/mapdemo.jpg")));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(30, 30 * (h / w)); //相机的视野范围
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0); //相机的初始位置
        cam.update();
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        mapSprite.draw(batch);
        batch.end();
    }


    private void handleInput() {

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
        cam.update();
    }


    @Override
    public void dispose() {
        //释放资源
        mapSprite.getTexture().dispose();
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        float delta = Gdx.graphics.getDeltaTime();
        //左移相机
        if (keycode==Input.Keys.A) {
            cam.translate(-3*delta, 0, 0);
        }
        //右移相机
        if (keycode==Input.Keys.D) {
            cam.translate(3*delta, 0, 0);
        }
        //下移相机
        if (keycode==Input.Keys.S) {
            cam.translate(0, -3*delta, 0);
        }
        //上移相机
        if (keycode==Input.Keys.W) {
            cam.translate(0, 3*delta, 0);
        }
        //逆时针旋转相机
        if (keycode==Input.Keys.Q) {
            cam.rotate(-rotationSpeed, 0, 0, 1);
        }
        //顺时针旋转相机
        if (keycode==Input.Keys.E) {
            cam.rotate(rotationSpeed, 0, 0, 1);
        }
        //回到初始化
        if (keycode==Input.Keys.R) {
            cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
            cam.zoom = 1;
        }
        //渲染
        handleInput();
        cam.update();
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
        //放大相机
        if (amountY<=-1) {
            cam.zoom += 0.05;
        }
        //缩小相机
        if (amountY>=1) {
            cam.zoom -= 0.05;
        }
        cam.update();
        return false;
    }
}

