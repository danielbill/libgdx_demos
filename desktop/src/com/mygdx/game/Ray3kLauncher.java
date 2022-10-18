package com.mygdx.game;
/*
Time : 22/10/16 6:44    
Author : 毕磊              
Site : ---                 
File : Ray3kLauncher.java          
Project: libgdx_test   
说明:
*/

import com.mygdx.ray3k.GameController;
import mygdx.core.DefLauncher;

public class Ray3kLauncher {
    public static void main(String[] args) {
        DefLauncher.defLaunch(new GameController(),"r3k实例");
    }
}
