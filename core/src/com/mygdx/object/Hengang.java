package com.mygdx.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * 对，就是个横杠
 */
public class Hengang {
    int width=100;
    int height=20;
    int minX;
    int maxX;

    int x;
    int y=50;

    public Hengang(){

        x=Gdx.graphics.getWidth()/2;

    }

    private void update(){
        x = Gdx.input.getX();
        minX = x;
        maxX = minX+width;
    }

    public int getMinX(){
        return minX;
    }

    public int getMaxX(){
        return maxX;
    }

    public int getY(){
        return y;
    }

    public void draw(ShapeRenderer shape) {
        update();
        shape.rect(Gdx.input.getX(),y,
                width,height);

    }

}
