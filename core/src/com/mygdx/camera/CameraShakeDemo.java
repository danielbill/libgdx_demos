package com.mygdx.camera;
/*
Time : 22/10/20 6:53    
Author : 毕磊              
Site : ---                 
File : CameraDemo2.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import danbl.game.core.World;

public class CameraShakeDemo extends Game {
    public static World world;
    public InputMultiplexer multiplexer; // 多输入接收器

    @Override
    public void create() {
        multiplexer = new InputMultiplexer(); // 多输入接收器
        Gdx.input.setInputProcessor(multiplexer);
        world = new World(new Sprite(new Texture(Gdx.files.internal("map/mapdemo.jpg"))));
        this.screen = new GameScreen(this,world);


    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
