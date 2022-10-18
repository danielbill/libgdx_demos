package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.object.Ball;
import com.mygdx.object.Hengang;

/**
 * 接球
 */
public class Game2 extends ApplicationAdapter {
    private Ball ball;
    private ShapeRenderer shape;
    private Hengang hg;


    @Override
    public void create() {
        ball = new Ball(10,400,30,2,2);
        hg = new Hengang();
        shape = new ShapeRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        hg.draw(shape);
        ball.draw(shape,hg);
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
