package com.mygdx.game;
/*
Time : 22/10/16 6:44    
Author : 毕磊              
Site : ---                 
File : Ray3kLauncher.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.ray3k.StageSwitcher;

public class StageSwitchLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("stage切换demo");
        config.setWindowedMode(500, 900);
//        config.setResizable(true);
        config.useVsync(true);
        config.setForegroundFPS(60);
        new Lwjgl3Application(new StageSwitcher(), config);
    }
}
