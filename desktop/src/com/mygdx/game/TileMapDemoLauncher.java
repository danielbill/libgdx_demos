package com.mygdx.game;
/*
Time : 22/10/22 9:38    
Author : 毕磊              
Site : ---                 
File : TileMapDemoLauncher.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.tiledmap.TiledTestDemo;

public class TileMapDemoLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("tile 地图测试");
        config.setWindowedMode(800, 600);
//        config.setResizable(true);
        config.useVsync(true);
        config.setForegroundFPS(60);
        new Lwjgl3Application(new TiledTestDemo(), config);
    }
}
