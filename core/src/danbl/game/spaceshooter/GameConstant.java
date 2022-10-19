package danbl.game.spaceshooter;

import java.util.Random;

public interface GameConstant {
    int TOWARDS_UP = 1;
    int TOWARDS_DOWN = -1;
    Random r = new Random();

    class GAME_STAT{
        final public static int PAUSED = 0;
        final public static int RUNNING = 1;
    }

}
