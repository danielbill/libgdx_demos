package danbl.game.core;
/*
Time : 22/10/20 21:11    
Author : 毕磊              
Site : ---                 
File : GameScreen.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import danbl.game.core.effect.ScreenShaking;

public abstract class GameScreen implements Screen {
    public GameController game;
    public World world;
    public Stage stage;
    public TextureAtlas ta;
    private final Camera camera;
    private final Viewport viewport;
    private final SpriteBatch batch;
    private Sprite bgSprite;
    private ScreenShaking screenShaking;
    private PlayerController playerController;

    public GameScreen(GameController game, World world) {
        this.game = game;
//        game.multiplexer.addProcessor(this);
        this.world = world;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(); //相机的视野范围
        viewport = new StretchViewport(world.WORLD_WIDTH,world.WORLD_HEIGHT, camera);
        stage = new Stage(viewport, batch);
        game.multiplexer.addProcessor(stage);
        stage.addActor(world);
        loadResourceAndInitActors();
    }
    protected abstract void loadResourceAndInitActors();


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
//        cameraFollowPlayer(playerController.getPlayer());
        camera.update();
        batch.begin();
        renderScreen(batch,delta);
        shakeScreen(batch, delta);
        batch.end();
        stage.act();
        stage.draw();
    }

    abstract void renderScreen(Batch batch,float delta);

//    private void cameraFollowPlayer(Player player) {
//        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
//        batch.setProjectionMatrix(camera.combined);
//        camera.update();
//    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    private void shakeScreen(Batch batch, float delta) {
        if (screenShaking == null) return;
        Gdx.app.log("shake test","in shakeScreen method");
        screenShaking.shakeScreen(delta);
        if(!screenShaking.isShaking()){
            this.screenShaking=null;
        }
    }


    @Override
    public void dispose() {
        batch.dispose();
        bgSprite.getTexture().dispose();
    }


    public Camera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Stage getStage() {
        return stage;
    }

    public void shakeCamera(float shakeDuration,int shakeStrength){
        screenShaking = new ScreenShaking(camera,shakeDuration,shakeStrength);
    }
}
