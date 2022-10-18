package com.mygdx.game;
/*
Time : 22/10/15 18:28    
Author : 毕磊              
Site : ---                 
File : FreeType.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class FreeType extends ApplicationAdapter {
    private Stage stage;
    BitmapFont font;
    SpriteBatch batch;

    @Override
    public void create () {
        batch = new SpriteBatch();
//        stage = new Stage(new ScreenViewport());
//        int Help_Guides = 12;
//        int row_height = Gdx.graphics.getWidth() / 12;
//        int col_width = Gdx.graphics.getWidth() / 12;
//        addBackgroundGuide(Help_Guides);

//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("truetypefont/Amble-Light.ttf"));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("SIMKAI.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderWidth = 1;
        parameter.color = Color.YELLOW;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        parameter.characters = "今天气很好，重复重复重复风和日丽！1234567890·zxcvbm";
        font = generator.generateFont(parameter); // font size 24 pixels
        generator.dispose();
//
//        Label.LabelStyle labelStyle = new Label.LabelStyle();
//        labelStyle.font = font24;
//
//        Label label2 = new Label("i ohohooh  i guess !",labelStyle);
//        label2.setSize(Gdx.graphics.getWidth()/Help_Guides*5,row_height);
//        label2.setPosition(col_width*2,Gdx.graphics.getHeight()-row_height*4);
//        stage.addActor(label2);


    }

    public void addBackgroundGuide(int columns){
        Texture texture = new Texture(Gdx.files.internal("bucket.png"));
        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);

        TextureRegion textureRegion = new TextureRegion(texture);
        textureRegion.setRegion(0,0,texture.getWidth()*columns,texture.getWidth()*columns);
        Image background = new Image(textureRegion);
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
        background.setPosition(0,Gdx.graphics.getHeight()-background.getHeight());
        stage.addActor(background);
    }

    @Override
    public void render () {
//        Gdx.gl.glClearColor(1, 1, 1, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        stage.act();
//        stage.draw();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, "11111今天天气很好，风和日丽重复！rtcx", 100, 100);
        batch.end();

    }
}
