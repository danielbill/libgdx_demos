package com.mygdx.game;
/*
Time : 22/10/17 12:38    
Author : 毕磊              
Site : ---                 
File : SpaceShooterLauncher.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import danbl.game.spaceshooter.controller.GameController;

public class SpaceShooterLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("经典复刻-太空射击");
        config.setWindowedMode(450, 800);
        config.setResizable(false);
        config.useVsync(true);
        config.setForegroundFPS(20);
        new Lwjgl3Application(new GameController(), config);
    }
}
