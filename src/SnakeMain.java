/*
    *
    * Game Snake made by Aleksandrs Porošins
    * This projects mades for rise practical skills and work with git
    *
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;
public class SnakeMain {
    static int width_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
    static int height_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
    static int width_app = 1280;
    static int height_app = 720;
    static int block = 40;
    static int gridW[]=new int[width_app/block];
    static int gridH[]=new int[ height_app/block];
    static int rec_x=gridW[gridW.length/2];//start position X
    static int rec_y=gridH[gridH.length/2];//start position Y
    public gameScreen gS = new gameScreen("Snake");
    public moveSnake mS = new moveSnake();
    public static void main (String[] args) {
        new SnakeMain();
    }
    public class moveSnake{
        public int x=0,y=0;
        public String moveTo= "right";
        public void up() {
            moveTo="up";
        }
        public void down(){
            moveTo="down";
        }
        public void left(){
            moveTo="left";
        }
        public void right(){
            moveTo="right";
        }

    }
    public static class DPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.green);
            g2d.setStroke(new BasicStroke(1));
            Line2D line;

            //Draw grid X and Y
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

            //Draw Hero
            Rectangle2D rect = new Rectangle2D.Double(rec_x, rec_y, block, block);
            g2d.setColor(Color.red);
            g2d.fillRect((int)rect.getX(),(int)rect.getY(),block,block);
            g2d.draw(rect);
            setBackground(Color.black);

        }
    }
    public class gameScreen extends JFrame {
        public gameScreen(String title) throws HeadlessException {
            super(title);
            DPanel dPanel = new DPanel();
            setSize(width_app,height_app);
            setBounds(width_Screen/2-width_app/2,height_Screen/2-height_app/2,width_app,height_app);
            //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close on X
            // setResizable(false);
            setUndecorated(true);//without title bar
            getContentPane().add(dPanel);
            setVisible(true);
            addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }
                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    int key = e.getKeyChar();
                    if (key == 119 || key == 87){// W
                        mS.up();
                    }
                    if (key == 83 || key == 115){ // S
                        mS.down();
                    }
                    if (key == 97 || key == 65){ // A
                        mS.left();
                    }
                    if (key == 100 || key == 68){ // D
                        mS.right();
                    }
                }
            });
            new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (mS.moveTo){
                        case "up":
                            mS.y -= block;
                            break;
                        case "down":
                            mS.y += block;
                            break;
                        case "left":
                            mS.x-=block;
                            break;
                        case "right":
                            mS.x+=block;
                            break;
                    }
                    rec_x = mS.x;
                    rec_y = mS.y;
                    System.out.println(rec_x +" "+rec_y);
                    gS.repaint();
                }
            }).start();
        }
    }
}