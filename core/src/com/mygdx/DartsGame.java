package com.mygdx;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.stage.LoadingStage;
import var3d.net.center.VGame;
import var3d.net.center.VListener;

public class DartsGame extends VGame {


    public DartsGame(VListener varListener) {
        super(varListener);
        this.setSize(480, 320);
    }

    @Override
    public void init() {
        this.load(Music.class, "audio/background.ogg");
        this.load(TextureAtlas.class, "pack/sha/default.pack");
        this.loadAll(Sound.class, "audio/bing.ogg", "audio/great.ogg");

        this.setStageLoad(LoadingStage.class);
//        this.setStage(ShaScreen.class);
    }
}


