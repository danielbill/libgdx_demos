package danbl.game.core.misc;
/*
Time : 22/10/21 5:55    
Author : 毕磊              
Site : ---                 
File : MyShapeDrawer.java          
Project: libgdx_test   
说明:
*/
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class MyShapeDrawer implements Disposable {
    public ShapeDrawer drawer;
    private SpriteBatch batch;
    private Texture texture;
    private TextureRegion region;
//    private Pixmap pixmap;

    public MyShapeDrawer(SpriteBatch batch) {
        this.batch = batch;
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        region = new TextureRegion(texture, 0, 0, 1, 1);
        drawer = new ShapeDrawer(batch,region);
        pixmap.dispose();
    }
    public MyShapeDrawer(SpriteBatch batch,Color color) {
       this(batch);
        drawer.setColor(color);
    }

    public void setColor(Color color){
        drawer.setColor(color);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        drawer= null;
    }


}
