package danbl.game.spaceshooter.controller;
/*
Time : 22/10/17 12:11    
Author : 毕磊              
Site : ---                 
File : GameController.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import danbl.game.spaceshooter.view.GameScreen;

public class GameController extends Game implements InputProcessor {

    GameScreen screen;
    Music bgMusic ;

    private boolean paused;

    @Override
    public void create() {
        screen = new GameScreen(this);
        setScreen(screen);
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/BGM.mp3"));
        bgMusic.play();
        bgMusic.setLooping(true);
        bgMusic.setVolume(0.7f);
        this.paused = false;
        Gdx.input.setInputProcessor(this);

    }

    public void stopMusic(){
        bgMusic.stop();
    }

    @Override
    public void resize(int width, int height) {
        screen.resize(width, height);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        screen.dispose();
        bgMusic.dispose();
    }


    public boolean isPaused(){
        return this.paused;
    }
    public void updatePaused(){
        this.paused=!this.paused;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode==Input.Keys.SPACE) {
            updatePaused();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
