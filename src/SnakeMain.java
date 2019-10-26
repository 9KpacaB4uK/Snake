import javax.swing.*;
import java.awt.*;

public class SnakeMain {
    int width_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
    int height_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
    int width_app = 1280;
    int height_app = 720;
    public static void main(String[] args){
        new SnakeMain().gameScreen();
        new SnakeMain().go();
    }
    void gameScreen(){
        JFrame frame = new JFrame();
        frame.setTitle("Snake");
        frame.setBounds(width_Screen/2-width_app/2,height_Screen/2-height_app/2,width_app,height_app);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//zakrivaet programu na krestik
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
    }
    void hero(){

    }
    void go(){

    }
}