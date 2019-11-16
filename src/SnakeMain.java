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
import java.util.Random;
import javax.swing.Timer;
public class SnakeMain {
    //Get size of monitor/screen
    static int width_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
    static int height_Screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();

    //Set game screen
    static int width_app = 1280;
    static int height_app = 720;

    //grid screen
    static int block = 40;//40
    static int gridW[] = new int[width_app/block];
    static int gridH[] = new int[height_app/block];

    //position of snake ,each part of snake
    static int rec_x[] = new int[gridW.length*gridH.length];
    static int rec_y[] = new int[gridW.length*gridH.length];
    Random rand = new Random();
    int food_x = rand.nextInt(gridW.length)*block;
    int food_y = rand.nextInt(gridH.length)*block;
    gameScreen gS = new gameScreen("Snake");
    foodSpawn fS = new foodSpawn();
    int snakeSize = 1;
    String moveTo = "left";
    boolean gameOver = false;
    public static void main (String[] args) {
        rec_x[0] = (gridW.length-1)/2*block;//start position X
        rec_y[0] = (gridH.length-1)/2*block;//start position Y
        new SnakeMain();
    }

    public class foodSpawn {
        public void spawn() {
            food_x = rand.nextInt(gridW.length)*block;
            food_y = rand.nextInt(gridH.length)*block;
        }
    }
    public class DPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.green);
            g2d.setStroke(new BasicStroke(1));
            setBackground(Color.black);
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
            Rectangle2D rect;
            for(int i = snakeSize;i > 0;i--) {
                //get position of prev
                rec_x[i] = rec_x[i - 1];
                rec_y[i] = rec_y[i - 1];
                //draw
                rect = new Rectangle2D.Double(rec_x[i], rec_y[i], block, block);
                g2d.setColor(Color.red);
                g2d.fillRect((int) rect.getX(), (int) rect.getY(), block, block);
                g2d.draw(rect);
            }
            //Draw Food
            Rectangle2D food = new Rectangle2D.Double(food_x, food_y, block, block);
            g2d.setColor(Color.yellow);
            g2d.fillRect((int)food.getX(),(int)food.getY(),block,block);
            g2d.draw(food);
        }
    }
    public class gameScreen extends JFrame {
        public gameScreen(String title) throws HeadlessException {
            super(title);
            DPanel dPanel = new DPanel();
            setSize(width_app,height_app);
            setBounds(width_Screen/2-width_app/2,height_Screen/2-height_app/2,width_app,height_app);
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
                        moveTo = "up";
                    }
                    if (key == 83 || key == 115){ // S
                        moveTo = "down";
                    }
                    if (key == 97 || key == 65){ // A
                        moveTo = "left";
                    }
                    if (key == 100 || key == 68){ // D
                        moveTo = "right";
                    }
                }
            });
            new Timer(200, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(gameOver == true){
                        System.out.println("exit");
                        System.exit(1);
                    }
                    //first element of snake movement
                    switch (moveTo){
                        case "up":
                            rec_y[0] -= block;
                            break;
                        case "down":
                            rec_y[0] += block;
                            break;
                        case "left":
                            rec_x[0] -= block;
                            break;
                        case "right":
                            rec_x[0] += block;
                            break;
                    }
                    //if snake out of field
                    if((rec_x[0] > width_app || rec_x[0] < 0) || (rec_y[0] > height_app || rec_y[0] < 0)){
                        gameOver = true;
                    }
                    //if ate a food
                    if(rec_x[0] == food_x && rec_y[0] == food_y){
                        fS.spawn();
                        snakeSize++;
                    }
                    gS.repaint();
                }
            }).start();
        }
    }
}