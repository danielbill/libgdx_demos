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
import com.badlogic.gdx.audio.Music;
import danbl.game.spaceshooter.view.GameScreen;

public class GameController extends Game {

    GameScreen screen;
    Music bgMusic ;

    @Override
    public void create() {
        screen = new GameScreen();
        setScreen(screen);
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/BGM.mp3"));
        bgMusic.play();
        bgMusic.setLooping(true);
        bgMusic.setVolume(0.7f);

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
}
