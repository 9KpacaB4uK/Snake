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

    public int snakeSize = 3;
    boolean gameOver = false;
    boolean changedposition = false; //changes when snake change position
    String moveTo = "right";
    Random rand = new Random();
    int food_x = rand.nextInt(Variables.gridW.length)*Variables.blockW;
    int food_y = rand.nextInt(Variables.gridH.length)*Variables.blockH;
    int deleyTimer = 90;
    Timer timer = new Timer(deleyTimer, null);
    gameScreen gS = new gameScreen();

    class food{

        public void spawn() {
            food_x = rand.nextInt(Variables.gridW.length)*Variables.blockW;
            food_y = rand.nextInt(Variables.gridH.length)*Variables.blockH;
            for(int i = 0; i<snakeSize;i++){
                if(food_x == rec_x[i] && food_y == rec_y[i]) {
                    spawn();
                }
            }
        }
    }

    class DPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            setBackground(Color.black);
            g2d.setColor(Color.green);
            Line2D line;
            //Draw grid X and Y
            int step = 0;
            for(int i=0;i<Variables.gridW.length;i++){
                //step--;
                step+=Variables.blockW;
                line = new Line2D.Double(step,Variables.height_app,step,-1);
                Variables.gridW[i] = step;
                g2d.draw(line);
            }
            step = 0;
            for(int i=0;i<Variables.gridH.length;i++){
                //step--;
                step+=Variables.blockH;
                Variables.gridH[i]= step;
                line = new Line2D.Double(-1,step,Variables.width_app,step);
                g2d.draw(line);
            }

            //Draw Hero
            Rectangle2D rect;
            for(int i = snakeSize;i > 0;i--) {
                //get position of prev
                rec_x[i] = rec_x[i - 1];
                rec_y[i] = rec_y[i - 1];
                //draw
                rect = new Rectangle2D.Double(rec_x[i], rec_y[i], Variables.blockW, Variables.blockH);
                g2d.setColor(Color.red);
                g2d.fillRect((int) rect.getX(), (int) rect.getY(), Variables.blockW, Variables.blockH);
                g2d.draw(rect);
            }
            //Draw Food
            Rectangle2D food = new Rectangle2D.Double(food_x, food_y, Variables.blockW, Variables.blockH);
            g2d.setColor(Color.yellow);
            g2d.fillRect((int)food.getX(),(int)food.getY(),Variables.blockW,Variables.blockH);
            g2d.draw(food);
        }
    }
    class gameScreen extends JFrame {
        public gameScreen() throws HeadlessException {
            JPanel gameBordersFrame = new JPanel();
            DPanel dPanel = new DPanel();
            gameBordersFrame.setSize(50,50);
            gameBordersFrame.setBackground(Color.gray);

            rec_x[0] = (Variables.gridW.length-1)/2*Variables.blockW;//start position X
            rec_y[0] = (Variables.gridH.length-1)/2*Variables.blockH;//start position Y
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
                    if ((key == 119 || key == 87) && moveTo!="down" && changedposition == true){// W
                        moveTo = "up";
                    }
                    if ((key == 83 || key == 115) && moveTo!="up" && changedposition){ // S
                        moveTo = "down";
                    }
                    if ((key == 97 || key == 65) && moveTo!="right" && changedposition){ // A
                        moveTo = "left";
                    }
                    if ((key == 100 || key == 68) && moveTo!="left" && changedposition){ // D
                        moveTo = "right";
                    }
                    changedposition = false;
                }
            });

            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(gameOver == true){
                        timer.stop();
                        new SnakeMenu();
                        gS.setVisible(false);
                        gS.dispose();
                        gS.removeAll();
                        return;
                    }

                    //first element of snake movement
                    switch (moveTo){
                        case "up":
                            rec_y[0] -= Variables.blockH;
                            changedposition = true;
                            break;
                        case "down":
                            rec_y[0] += Variables.blockH;
                            changedposition = true;
                            break;
                        case "left":
                            rec_x[0] -= Variables.blockW;
                            changedposition = true;
                            break;
                        case "right":
                            rec_x[0] += Variables.blockW;
                            changedposition = true;
                            break;
                    }
                    //if snake out of field
                    if((rec_x[0] >= Variables.width_app || rec_x[0] <= -1) || (rec_y[0] >= Variables.height_app || rec_y[0] <= -1)){
                        gameOver = true;
                    }
                    //if snake ate yourself
                    for(int i = 1;i<=snakeSize;i++){
                        if(rec_x[0]==rec_x[i] && rec_y[0]==rec_y[i]){
                            gameOver = true;
                        }
                    }
                    //if ate a food
                    if(rec_x[0] == food_x && rec_y[0] == food_y){
                        fS.spawn();
                        snakeSize++;
                    }
                    gS.repaint();
                }
            });
            timer.start();

            getContentPane().add(dPanel);
            boolean toggleFullscreen = Variables.fullscreen; // toggle of fullscreen
            if(toggleFullscreen == true) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            else {
                setExtendedState(JFrame.NORMAL);
            }
            setSize(Variables.width_app,Variables.height_app);
            setBounds(Variables.centerX,Variables.centerY,Variables.width_app,Variables.height_app);
            setUndecorated(true);//without title bar
            setVisible(true);
        }
    }
}