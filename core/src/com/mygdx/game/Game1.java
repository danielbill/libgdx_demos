package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.object.Ball;

import java.util.ArrayList;
import java.util.Random;

public class Game1 extends ApplicationAdapter{
    ShapeRenderer shape;
    ArrayList<Ball> balls = new ArrayList<>();
    Random r = new Random();


    @Override
    public void create() {
        shape = new ShapeRenderer();
        for(int i=0;i<10;i++){
            balls.add(new Ball(r.nextInt(Gdx.graphics.getWidth()),
                    r.nextInt(Gdx.graphics.getHeight()),
                    r.nextInt(50),
                    r.nextInt(15),
                    r.nextInt(15))
            );
        }


    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        for(Ball ball : balls){
            ball.draw(shape,null);
        }
        shape.end();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
