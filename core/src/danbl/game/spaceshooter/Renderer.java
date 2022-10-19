package danbl.game.spaceshooter;
/*
Time : 22/10/19 10:41    
Author : 毕磊              
Site : ---                 
File : Renderer.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.graphics.g2d.Batch;
import danbl.game.spaceshooter.view.GameScreen;

public abstract class Renderer {
    protected GameScreen gs;
    public Renderer(GameScreen gs){
        this.gs=gs;
    }

    public abstract void render(Batch batch,float deltaTime);
}
