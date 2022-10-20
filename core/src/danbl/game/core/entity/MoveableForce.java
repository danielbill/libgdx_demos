package danbl.game.core.entity;
/*
Time : 22/10/18 11:51    
Author : 毕磊              
Site : ---                 
File : MoveableForce.java          
Project: libgdx_test   
说明:
*/

import danbl.game.spaceshooter.ForceType;

public abstract class MoveableForce extends MoveableObject implements ForceType {
    protected int forceType;


    public MoveableForce(float x, float y, float speed, int width, int height, int towards) {
        super(x, y, speed, width, height, towards);
    }

    public MoveableForce(float speed, int width, int height, int towards) {
        super(speed, width, height, towards);
    }

    @Override
    public void setForceType(int forceType) {
        this.forceType=forceType;
    }

    @Override
    public int getForceType() {
        return forceType;
    }


    public void detectCollideWithEnemy(MoveableForce mf){
        if(mf==null) return;
        if(isCollideWithEnemy(mf)){
            this.collided();
            mf.collided();
        }
    }

    public boolean isCollideWithEnemy(MoveableForce mf){
        if(mf.getForceType()==this.forceType) return false;
        return isCollideWith(mf);
    }



}
