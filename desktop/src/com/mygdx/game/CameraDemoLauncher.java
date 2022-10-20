package com.mygdx.game;
/*
Time : 22/10/19 17:02    
Author : 毕磊              
Site : ---                 
File : CameraDemoLauncher.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.camera.CameraDemo2;

public class CameraDemoLauncher {
    public static void main(String[] args) {

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("camera test");
        config.setWindowedMode(640, 360);
//        config.setResizable(false);
        config.useVsync(true);
        config.setForegroundFPS(60);
        new Lwjgl3Application(new CameraDemo2(), config);
    }
}
