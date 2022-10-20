package danbl.game.spaceshooter.view;
/*
Time : 22/10/19 10:40    
Author : 毕磊              
Site : ---                 
File : InfoBar.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import danbl.game.spaceshooter.Renderer;

import java.util.Locale;

public class InfoBar extends Renderer {
    public BitmapFont eFont;
    public BitmapFont cFont;
    private TextureRegion hpTr;
    private TextureRegion shieldTr;
    private int score=0;
    private float bottomLineHeight=gs.WORLD_HEIGHT*0.985f;
    private int iconSize = 3;
    private float iconLine = bottomLineHeight-iconSize;
    private float iconGap = iconSize+0.5f;

    public void addScore(){
        score+=10;
    }
    private void initCFont(){
        FreeTypeFontGenerator ftt = new FreeTypeFontGenerator(
                Gdx.files.internal("font/酷站快乐字体.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size=48;
//        param.borderWidth=2f;
        param.color = new Color(1, 1, 1, 0.6f);
        //        param.characters = "得分血命1234567890";

        this.cFont = ftt.generateFont(param);
        cFont.getData().setScale(0.1f);
        ftt.dispose();
    }
    public InfoBar(GameScreen gs) {
        super(gs);
        FreeTypeFontGenerator ftt = new FreeTypeFontGenerator(
                Gdx.files.internal("font/Sounso-Quality-2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size=64;
        param.color = new Color(0.75f, 0.4f, 0.3f, 0.45f);
//        param.color = new Color(1f, 1f, 1f, 0.55f);
        this.eFont = ftt.generateFont(param);
        eFont.getData().setScale(0.1f);
        hpTr = gs.ta.findRegion("playerLife1_blue");
        shieldTr = gs.ta.findRegion("shield_silver");
    }


    @Override
    public void render(Batch batch, float deltaTime) {
        eFont.draw(batch, String.format(Locale.getDefault(),"%04d",score),5,bottomLineHeight);
        if(gs.getPlayerController().getPlayer()==null) return;
        for (int i = 0; i < gs.getPlayerController().getPlayer().getShieldHP(); i++) {
            batch.draw(shieldTr,gs.WORLD_WIDTH*0.5f+i*iconGap,iconLine,iconSize,iconSize);
        }
        for (int i = 0; i < gs.getPlayerController().getPlayer().getHp(); i++) {
            batch.draw(hpTr,gs.WORLD_WIDTH*0.8f+i*iconGap,iconLine,iconSize,iconSize);
        }
    }

    public void drawPause(Batch batch){
        if(gs.getGameCtrl().isPaused()) {
//            Gdx.app.log("pause"," draw sth 123456 !");
            eFont.draw(batch, "PAUSE...", gs.WORLD_WIDTH / 2 - 10, gs.WORLD_HEIGHT / 2);
        }
    }
}
