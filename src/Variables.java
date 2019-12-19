import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Variables {
    //Get size of monitor/screen
    static int width_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
    static int height_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();

    //Set game screen
    static int width_app = 1280;
    static int height_app = 720;

    //grid screen
    final static int block = 40;//40
    static int gridW[] = new int[width_app/block];
    static int gridH[] = new int[height_app/block];

    //game screens activity
    static byte activity = 1 ; // 0 - menu screen, 1 - game screen, 2 - settings

    static int deleyTimer = 10;
    static Timer timer = new Timer(deleyTimer, null);
    public static void main(String[] args) {
        switch (activity){
            case 0:
                new SnakeMain();
                break;
            case 1:
                new SnakeMenu();
                break;
            case 2:
                break;
        }
    }
}
