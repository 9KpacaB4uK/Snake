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

    //game screens activity
    byte activity = 0 ; // 0 - menu screen, 1 - game screen, 2 - settings

    //position of snake ,each part of snake
    static int rec_x[] = new int[gridW.length*gridH.length];
    static int rec_y[] = new int[gridW.length*gridH.length];
    Random rand = new Random();
    int food_x = rand.nextInt(gridW.length)*block;
    int food_y = rand.nextInt(gridH.length)*block;
    gameScreen gS = new gameScreen("Snake");
    int snakeSize = 1;
    String moveTo = "right";
    boolean gameOver = false;
    int deleyTimer = 0;
    Timer timer = new Timer(deleyTimer, null);
    public static void main (String[] args) {
        new SnakeMain();
    }

    public class food{
        public void spawn() {
            food_x = rand.nextInt(gridW.length)*block;
            food_y = rand.nextInt(gridH.length)*block;
        }
    }
    public class Dmenu extends JPanel{
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(Color.black);
        }
    }

    public class DPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.green);
            //g2d.setStroke(new BasicStroke(1));
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
            System.out.println("act: "+activity);
            setSize(width_app,height_app);
            setBounds(width_Screen/2-width_app/2,height_Screen/2-height_app/2,width_app,height_app);

            switch (activity){
                case 0:
                    Dmenu dmenu = new Dmenu();
                    Font font1 = new Font("Arial", Font.BOLD, block);

                    //create buttons
                    Button butPlay,butExit,butSettings;

                    butPlay = new Button("PLAY");
                    butPlay.setBounds(block*3, height_app/2-block*3/2, block*10, block*3);
                    butPlay.setBackground(Color.green);
                    butPlay.setFont(font1);

                    butExit = new Button("EXIT");
                    butExit.setBounds(butPlay.getX(), butPlay.getY() + butPlay.getHeight()+ block, butPlay.getWidth(), butPlay.getHeight());
                    butExit.setBackground(Color.red);
                    butExit.setFont(font1);

                    butSettings = new Button("SETTINGS");
                    butSettings.setBounds(butPlay.getX(), butPlay.getY() - butPlay.getHeight()- block, butPlay.getWidth(), butPlay.getHeight());
                    butSettings.setBackground(Color.yellow);
                    butSettings.setFont(font1);

                    butPlay.addActionListener(new Listener());
                    butSettings.addActionListener(new Listener());
                    butExit.addActionListener(new Listener());

                    //add to display
                    getContentPane().add(butSettings);
                    getContentPane().add(butPlay);
                    getContentPane().add(butExit);
                    getContentPane().add(dmenu);

                    break;
                case 1:
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
                    deleyTimer = 120;
                    timer = new Timer(deleyTimer, new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if(gameOver == true){
                                activity = 0;
                                timer.stop();
                                gS = new gameScreen("snake");
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
                    timer.start();
                    getContentPane().add(dPanel);
                    break;
                case 2:
                    //TO DO button "SETTINGS"
                    break;
            }

            setUndecorated(true);//without title bar
            setVisible(true);
        }
        private class Listener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                switch (e.getActionCommand()){
                    case "PLAY":
                        gameOver = false;
                        snakeSize = 1;
                        activity = 1;
                        gS = new gameScreen("snake");
                        break;
                    case "EXIT":
                        System.out.println("exit");
                        System.exit(1);
                        break;
                    case "SETTINGS":
                       activity = 2;//TO DO
                        break;
                }
            }
        }
    }
}