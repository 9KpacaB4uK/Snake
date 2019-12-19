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
class SnakeMain {
    //position of snake ,each part of snake
    int rec_x[] = new int[Variables.gridW.length*Variables.gridH.length];
    int rec_y[] = new int[Variables.gridW.length*Variables.gridH.length];
    public int snakeSize = 5;
    int activity = Variables.activity;
    int gridW[] = Variables.gridW;
    int gridH[] = Variables.gridH;
    int block = Variables.block;
    int height_app = Variables.height_app;
    int width_app = Variables.width_app;
    int height_Screen = Variables.height_Screen;
    int width_Screen = Variables.width_Screen;
    boolean gameOver = false;
    String moveTo = "right";
    Random rand = new Random();
    int food_x = rand.nextInt(Variables.gridW.length)*Variables.block;
    int food_y = rand.nextInt(Variables.gridH.length)*Variables.block;
    gameScreen gS = new gameScreen();
    public static void main(String[] args) {
        new SnakeMain();
    }
    class food{

        public void spawn() {
            food_x = rand.nextInt(Variables.gridW.length)*Variables.block;
            food_y = rand.nextInt(Variables.gridH.length)*Variables.block;
        }
    }
    /*public class Dmenu extends JPanel{
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(Color.black);
        }
    }*/

    class DPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            //g2d.setStroke(new BasicStroke(1));
            setBackground(Color.black);
            g2d.setColor(Color.green);
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
    class gameScreen extends JFrame {
        public gameScreen() throws HeadlessException {

            System.out.println("act: "+activity);
            setSize(width_app,height_app);
            setBounds(width_Screen/2-width_app/2,height_Screen/2-height_app/2,width_app,height_app);

            rec_x[0] = (gridW.length-1)/2*block;//start position X
            rec_y[0] = (gridH.length-1)/2*block;//start position Y
            DPanel dPanel = new DPanel();
            food fS = new food();
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
            Variables.deleyTimer = 120;

            Variables.timer = new Timer(Variables.deleyTimer, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if(gameOver == true){
                        Variables.timer.stop();
                        new SnakeMenu();
                        return;
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
            });
            Variables.timer.start();
            getContentPane().add(dPanel);

            setUndecorated(true);//without title bar
            setVisible(true);
        }
    }
}