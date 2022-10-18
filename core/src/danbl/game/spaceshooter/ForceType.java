package danbl.game.spaceshooter;

/***
 * 阵营
 */
public interface ForceType {
    public final int FORCE_PLAYER = 0;
    public final int FORCE_ENEMY = 1;
    public void setForceType(int forceType);
    public int getForceType();
}
