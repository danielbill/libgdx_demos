package danbl.game.spaceshooter.entity;
/*
Time : 22/10/18 7:34    
Author : 毕磊              
Site : ---                 
File : Bullet.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import danbl.game.spaceshooter.ai.MovementAI;

public abstract class MoveableObject {
    protected float x;
    protected float y;
    protected float speed;
    protected int width;
    protected int height;
    protected int towards;
    protected Rectangle boundingBox;
    protected MovementAI moveAI;


    public MoveableObject(float x, float y, float speed, int width, int height, int towards) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.towards = towards;
        boundingBox  = new Rectangle(x,y,width,height);
    }
    public MoveableObject(float speed, int width, int height,int towards) {
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.towards = towards;
    }

    protected void syncBoundingBoxWithObject(float deltaTime){
        boundingBox.x=this.x;
        boundingBox.y=this.y;
    }

    public void move(float xOffset,float yOffset){
        this.x+=xOffset;
        this.y+=yOffset;
    }

    protected void baseDeltaTimeUpdate(float deltaTime){
        update(deltaTime);
        syncBoundingBoxWithObject(deltaTime);
    }

    public abstract void update(float deltaTime);

    public abstract void draw(Batch batch);

    public void render(Batch batch,float deltaTime){
        baseDeltaTimeUpdate(deltaTime);
        draw(batch);
    }

    public boolean isCollideWith(MoveableObject mo){
        return this.boundingBox.overlaps(mo.getBoundingBox());
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSpeed() {
        return speed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTowards() {
        return towards;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setMoveAI(MovementAI moveAI) {
        this.moveAI = moveAI;
    }

    public void moveByAI(float deltaTime){
        if(this.moveAI==null) return;
        this.moveAI.makeMove(deltaTime,this);
    }

    public abstract void collided();
}
