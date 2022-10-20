package danbl.game.spaceshooter.effect;
/*
Time : 22/10/19 20:53    
Author : 毕磊              
Site : ---                 
File : ScreenShaking.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import danbl.game.spaceshooter.GameConstant;

import java.util.Random;

public class ScreenShaking {
    private Camera camera;
    private float shakeCameraTimer = 0;
    private float shakeCameraDuration;
    private int shakeCameraStrength;
    private boolean isShaking;
    private float OriginalX;
    private float OriginalY;
    private boolean cameraFollowPlayer =true;

    public ScreenShaking(Camera camera, float shakeCameraDuration, int shakeCameraStrength) {
        this.camera = camera;
        this.shakeCameraDuration = shakeCameraDuration;
        this.shakeCameraStrength = shakeCameraStrength;
        isShaking = false;
    }

    public ScreenShaking(Camera camera, float shakeCameraDuration, int shakeCameraStrength,boolean isCameraFollowPlayer) {
        this(camera,shakeCameraDuration,shakeCameraStrength);
        this.cameraFollowPlayer=isCameraFollowPlayer;
    }

    public boolean isShaking() {
        return isShaking;
    }

    public void shakeScreen(float delta) {
        Gdx.app.log("shake", "in shakeScreen method, timer is "+shakeCameraTimer
                +"; duration is "+shakeCameraDuration);
        if(shakeCameraTimer==0){
            isShaking = true;
            OriginalX = camera.position.x;
            OriginalY = camera.position.y;
            Gdx.app.log("shake", "shake begin OriginalX is "+OriginalX);
        }


        if (shakeCameraTimer >= shakeCameraDuration) {
            isShaking = false;
            shakeCameraTimer = 0;
            if(!cameraFollowPlayer) {
                //主程序会持续更新camera坐标，如果是跟随player，则此行无效
                camera.position.set(OriginalX, OriginalY, 0);
                camera.update();
            }
            Gdx.app.log("shake", "shake over!!!!!!!! OriginalX is "+OriginalX
            +" and camera position set to "+OriginalX+" "+OriginalY);
            return;
        }

        Random r = GameConstant.r;
        shakeCameraTimer += delta;
        float rf = r.nextFloat();
        float xOffset = r.nextFloat(shakeCameraStrength);
        float yOffset = r.nextFloat(shakeCameraStrength);
        xOffset = rf > 0.5 ? xOffset : xOffset * -1f;
        yOffset = rf > 0.5 ? yOffset : yOffset * -1f;
        if (Math.abs(camera.position.x + xOffset - OriginalX) > shakeCameraStrength + 1) {
            Gdx.app.log("shake-bug", "xoffset is too big " + xOffset);
            xOffset=-xOffset;
            Gdx.app.log("shake-bug", "reverse it to "+xOffset);
        }
        if (Math.abs(camera.position.y + yOffset - OriginalY) > shakeCameraStrength + 1) {
            Gdx.app.log("shake-bug", "yOffset is too big " + yOffset);
            yOffset=-yOffset;
            Gdx.app.log("shake-bug", "reverse it to "+yOffset);
        }
        camera.translate(xOffset, yOffset, 0);
        //        camera.update();
        Gdx.app.log("shake", "the cam shaked , offset is " + xOffset + " " + yOffset);

    }
}
