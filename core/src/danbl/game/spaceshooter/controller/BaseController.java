package danbl.game.spaceshooter.controller;
/*
Time : 22/10/18 12:06    
Author : 毕磊              
Site : ---                 
File : BaseController.java          
Project: libgdx_test   
说明:
*/

import danbl.game.spaceshooter.view.GameScreen;

public class BaseController {
    protected GameScreen gs;
    protected int downLimit = 0;
    protected int rightLimit;
    protected int upLimit;
    protected int leftLimit = 0;

    public BaseController(GameScreen gs) {
        this.gs = gs;
        this.rightLimit = gs.WORLD_WIDTH;
        this.upLimit = gs.WORLD_HEIGHT;
    }

    public void setDownLimit(int downLimit) {
        this.downLimit = downLimit;
    }

    public void setRightLimit(int rightLimit) {
        this.rightLimit = rightLimit;
    }

    public void setUpLimit(int upLimit) {
        this.upLimit = upLimit;
    }

    public void setLeftLimit(int leftLimit) {
        this.leftLimit = leftLimit;
    }
}
