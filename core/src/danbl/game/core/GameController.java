package danbl.game.core;
/*
Time : 22/10/20 21:15    
Author : 毕磊              
Site : ---                 
File : GameController.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;


public abstract class GameController extends Game {
    public World world;
    public InputMultiplexer multiplexer; // 多输入接收器

    @Override
    public void create() {
        multiplexer = new InputMultiplexer(); // 多输入接收器
        Gdx.input.setInputProcessor(multiplexer);
        init();
//        this.screen = new GameScreen(this,world);
    }

    abstract void init();

    @Override
    public void dispose() {
        super.dispose();
    }
}
