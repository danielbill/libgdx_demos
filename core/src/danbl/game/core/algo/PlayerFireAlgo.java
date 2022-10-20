package danbl.game.core.algo;
/*
Time : 22/10/19 5:59    
Author : 毕磊              
Site : ---                 
File : PlayerFireAI.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerFireAlgo {
    public static boolean isFiring(){
        return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
    }

}
