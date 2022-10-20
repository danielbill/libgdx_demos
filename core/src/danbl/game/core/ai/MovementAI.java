package danbl.game.core.ai;
/*
Time : 22/10/18 17:03    
Author : 毕磊              
Site : ---                 
File : MovementAI.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.math.Vector2;
import danbl.game.spaceshooter.GameConstant;
import danbl.game.spaceshooter.controller.BaseController;
import danbl.game.core.entity.MoveableObject;
import danbl.game.spaceshooter.view.GameScreen;

public class MovementAI extends BaseController {
    private Vector2 directionVector;
    private float timeSinceLastDirectionChange;
    private float directionChangeFrequency;
    private boolean moveInScreen=true;
    private float moveUpLimit;
    private float moveDownLimit;
    private float moveLeftLimit;
    private float moveRightLimit;

    public MovementAI(GameScreen gs,float directionChangeFrequency) {
        super(gs);
        this.directionVector = new Vector2(0,0);
        this.directionChangeFrequency = directionChangeFrequency;
        this.timeSinceLastDirectionChange=0;

    }

    public void bindMoveableObject(MoveableObject mo){
        mo.setMoveAI(this);
        this.moveLeftLimit = this.leftLimit;
        this.moveRightLimit = this.rightLimit - mo.getWidth();
        this.moveUpLimit = this.upLimit - mo.getHeight();
        this.moveDownLimit = this.downLimit;

    }

    public void makeMove(float deltaTime, MoveableObject mo){
        timeSinceLastDirectionChange+=deltaTime;
        if(timeSinceLastDirectionChange>=directionChangeFrequency){
            float bearing = GameConstant.r.nextFloat() *6.283f;
            directionVector.x=(float)Math.sin(bearing);
            directionVector.y=(float)Math.cos(bearing);
            timeSinceLastDirectionChange=0;
        }
        float moveOffset = mo.getSpeed()*deltaTime;
        float xOffset = directionVector.x*moveOffset;
        float yOffset = directionVector.y*moveOffset;
//        Gdx.app.log("3","this.moveLeftLimit." +this.moveLeftLimit);

        if(mo.getX()<=this.moveLeftLimit && xOffset<0){
            xOffset = 0;
        }
        if(mo.getX()>=this.moveRightLimit && xOffset>0){
            xOffset = 0;
        }
        if(mo.getY()<=this.moveDownLimit && yOffset<0){
            yOffset = 0 ;
        }
        if(mo.getY()>=this.moveUpLimit && yOffset>0){
            yOffset = 0 ;
        }
        mo.move(xOffset,yOffset);
    }

}
