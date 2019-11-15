/*
    *
    * Game Snake made by Aleksandrs Porošins
    * This projects mades for rise practical skills and work with git
    *
*/
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class SnakeMain {
    static int width_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
    static int height_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
    static int width_app = 1280;
    static int height_app = 720;
    static int block = 40;
    static int gridW[]=new int[width_app/block];
    static int gridH[]=new int[ height_app/block];
    public static void main(String[] args){
        gameScreen gS = new gameScreen("Snake");
        new SnakeMain().go();
    }

    public static class DPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.green);
            g2d.setStroke(new BasicStroke(1));
            Line2D line;
            int step = 0;
            for(int i=0;i<gridW.length;i++){
                gridW[i] = step;
                step+=block;
                line = new Line2D.Double(step,height_app,step,0);
                g2d.draw(line);
            }
            step = 0;
            for(int i=0;i<gridH.length;i++){
                gridH[i]= step;
                step+=block;
                line = new Line2D.Double(0,step,width_app,step);
                g2d.draw(line);
            }
            int rec_x=gridW[1-1];//start position X
            int rec_y=gridH[1-1];//start position Y
            Rectangle2D rect = new Rectangle2D.Double(rec_x, rec_y, block, block);
            g2d.setColor(Color.red);
            g2d.fillRect((int)rect.getX(),(int)rect.getY(),block,block);
            g2d.draw(rect);
            setBackground(Color.black);
        }
    }
    public static class gameScreen extends JFrame {
        public gameScreen(String title) throws HeadlessException {
            super(title);

            DPanel dPanel = new DPanel();

            setSize(width_app,height_app);
            setBounds(width_Screen/2-width_app/2,height_Screen/2-height_app/2,width_app,height_app);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close on X
           // setResizable(false);
            setUndecorated(true);//without title bar
            getContentPane().add(dPanel);
            setVisible(true);
        }

    }
    void hero(){
    }
    void go(){
    }
}