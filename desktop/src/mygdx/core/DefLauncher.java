package mygdx.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DefLauncher {
    private static final int defScreenWidth = 1280;
    private static final int defScreenHeight = 720;
    private static final int defFPS = 60;
    public static void defLaunch(ApplicationListener app, String appTitle){
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle(appTitle);
        config.setWindowedMode(defScreenWidth, defScreenHeight);
        config.useVsync(true);
        config.setForegroundFPS(defFPS);
        new Lwjgl3Application(app, config);
    }
}
