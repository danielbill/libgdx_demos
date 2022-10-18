package com.mygdx.game;

import mygdx.core.DefLauncher;
import var3d.net.center.desktop.VDesktopLauncher;

public class Game3Launcher {
    public static void main (String[] arg) {
        VDesktopLauncher varListener = new VDesktopLauncher(false) {
        };
        DefLauncher.defLaunch(new Game3(varListener),"我的堡垒");
    }
}
