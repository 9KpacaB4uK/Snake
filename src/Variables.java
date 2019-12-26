import com.sun.scenario.Settings;

import javax.swing.*;
import java.awt.*;

public class Variables {
    //Get size of monitor/screen
    static int width_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
    static int height_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();

    static  int textsize = 40;
    //Set game screen
    static int width_app = 1280;
    static int height_app = 720;

    //center of screen
    static int centerX = width_Screen/2-width_app/2;
    static int centerY = height_Screen/2-height_app/2;

    //grid screen
    static int gridW[] = new int[32];
    static int gridH[] = new int[15];
    static int blockW = width_app/gridW.length;//40
    static int blockH = height_app/gridH.length;
    static void blockUpdate(){
        blockW = width_app/gridW.length;
        blockH = height_app/gridH.length;
    }
    //game screens activity
    static byte activity = 0 ; // 0 - menu screen, 1 - game screen, 2 - settings
    //game settings
    static boolean fullscreen = false;
    public static void main(String[] args) {

        System.out.println("main Variables.java");
        switch (activity){
            case 0:
                new SnakeMenu();
                break;
            case 1:
                new SnakeMain();
                break;
            case 2:
                new SettingsGame();
                break;
        }
    }
}
