package danbi.ongdx.core;

import com.badlogic.gdx.InputProcessor;

public interface GameController extends InputProcessor {
    /**
     * 运行游戏
     */
    public void run();
    /**
     * 初始化游戏
     */
    public void init();

    /**
     * 加载游戏
     */
    public void loading();
    /**
     * 暂停游戏
     */
    public void pause();
    /**
     * 恢复游戏
     */
    public void resume();
    /**
     * 退出游戏
     */
    public void exit();
    /**
     * 返回游戏菜单
     */
    public void toMenu();

    /**
     * 进入游戏设置
     */
    public void toSetup();




}
