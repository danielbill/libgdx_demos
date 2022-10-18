
package com.mygdx.stage;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import var3d.net.center.VGame;
import var3d.net.center.VLabel;

public class LoadingStage extends BaseStage {
    public LoadingStage(VGame game) {
        super(game);
        setBackground(Color.BLACK);
        VLabel label = game.getLabel("加载中...").setColor(Color.WHITE).setPosition(160, 150).setColor(Color.WHITE)
                .getActor();
        addActor(label);

        Image image = new Image(new TextureAtlas(Gdx.files.internal("pack/loading/default.pack")).findRegion("ninja_attack"));

        game.getUI(image).setOrigin(image.getWidth() / 2, image.getHeight() / 2).setPosition(100, 140)
                .addAction(Actions.repeat(1000, Actions.rotateBy(360, 10f)));

        addActor(image);
    }

}
