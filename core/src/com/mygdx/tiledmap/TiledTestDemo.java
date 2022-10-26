package com.mygdx.tiledmap;
/*
Time : 22/10/22 8:35    
Author : 毕磊              
Site : ---                 
File : TiledTestDemo.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TiledTestDemo  implements ApplicationListener {
    Stage stage;
    int width=30*32;
    int height=20*32;
    private TiledMap map;
    private  OrthogonalTiledMapRenderer mapRenderer;
    private float unitScale = 1/32f;

    OrthographicCamera camera;
    Viewport viewport;

    SpriteBatch batch;


    @Override
    public void create() {
        map =new TmxMapLoader().load("map/def.tmx");
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        int z = 2;
        viewport = new FitViewport(width,height,camera);
//        viewport = new ScreenViewport(camera);
//        viewport.setScreenSize(width/z,height/z);
        viewport.setWorldSize(width/z,height/z);
        mapRenderer = new OrthogonalTiledMapRenderer(map,batch);
//        camera.zoom=z/2;
        Gdx.app.log("camera","width,height:"+width+" : "+height);
        camera.position.x=width/2/z;
        camera.position.y=height/2/z;
        Gdx.app.log("camera","x,y:"+camera.position.x+" : "+camera.position.y);
        camera.update();



    }
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.setView(camera);
        camera.update();
        mapRenderer.render();
//        Gdx.app.log("camera","x,y:"+camera.position.x+" : "+camera.position.y);
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            camera.position.y+=10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            camera.position.y-=10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            camera.position.x-=10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            camera.position.x+=10;
        }

//        stage.act();
//        stage.draw();
    }

    @Override
    public void dispose() {
// TODO Auto-generated method stub
        batch.dispose();

    }

    @Override
    public void pause() {
// TODO Auto-generated method stub

    }



    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resume() {
// TODO Auto-generated method stub

    }
}
