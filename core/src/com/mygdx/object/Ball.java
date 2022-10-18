package com.mygdx.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color color = Color.WHITE;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    private void update(Hengang hg) {


        if (collidesWith(hg)){
            ySpeed = -ySpeed;
        }
        if (x < 0 || x > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y < 0 || y > Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
        }
        x += xSpeed;
        y += ySpeed;

    }

    private void checkCollision(Hengang hg) {
        if(collidesWith(hg)){
            color = Color.RED;
        }
        else{
            color = Color.WHITE;
        }
    }

    private boolean collidesWith(Hengang hg){
        if (y>hg.getY()+size) return false;
        if((x+size/2)<hg.getMinX() | (x-size/2)>hg.getMaxX()) return false;
        return true;

    }

    public void draw(ShapeRenderer shape,Hengang hg) {
        update(hg);
        shape.circle(x, y, size);
        shape.setColor(color);
    }
}
