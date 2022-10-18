package danbi.ongdx;
/*
Time : 22/10/16 10:49    
Author : 毕磊              
Site : ---                 
File : BaseGameController.java          
Project: libgdx_test   
说明:
*/

import danbi.ongdx.core.GameController;

public class BaseGameController  implements GameController {
    @Override
    public void run() {

    }

    @Override
    public void init() {

    }

    @Override
    public void loading() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void toMenu() {

    }

    @Override
    public void toSetup() {

    }

    @Override
    public boolean keyDown(int keycode) {
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
