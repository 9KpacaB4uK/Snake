import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class SnakeMenu {
    int block = Variables.block;
    int height_app = Variables.height_app;
    int width_app = Variables.width_app;
    int width_Screen = Variables.width_Screen;
    int height_Screen = Variables.height_Screen;
    int deleyTimer = Variables.deleyTimer;
    MenuScreen ms = new MenuScreen();

    public class DPanel extends JPanel{
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(Color.black);
        }
    }
    public class MenuScreen extends JFrame {
        public MenuScreen() throws HeadlessException {

            setSize(width_app,height_app);
            setBounds(width_Screen/2-width_app/2,height_Screen/2-height_app/2,width_app,height_app);

            DPanel dpanel = new DPanel();
            Font font1 = new Font("Arial", Font.BOLD, block);

            //create buttons
            Button butPlay,butExit,butSettings;

            butPlay = new Button("PLAY");
            butPlay.setBounds(block*3, height_app/2-block*3/2, block*10, block*3);
            butPlay.setBackground(Color.green);
            butPlay.setFont(font1);

            butExit = new Button("EXIT");
            butExit.setBounds(butPlay.getX(), butPlay.getY() + butPlay.getHeight()+ block, butPlay.getWidth(), butPlay.getHeight());
            butExit.setBackground(Color.RED);
            butExit.setFont(font1);

            butSettings = new Button("SETTINGS");
            butSettings.setBounds(butPlay.getX(), butPlay.getY() - butPlay.getHeight()- block, butPlay.getWidth(), butPlay.getHeight());
            butSettings.setBackground(Color.yellow);
            butSettings.setFont(font1);

            butPlay.addActionListener(new Listener());
            butSettings.addActionListener(new Listener());
            butExit.addActionListener(new Listener());
            System.out.println("1");
            //add to display
            Variables.timer = new Timer(deleyTimer, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    ms.repaint();
                }
            });
            Variables.timer.start();
            getContentPane().add(butSettings);
            getContentPane().add(butPlay);
            getContentPane().add(butExit);
            getContentPane().add(dpanel);
            setUndecorated(true);//without title bar
            setVisible(true);
        }
    }
    class Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            switch (e.getActionCommand()){
                case "PLAY":
                    Variables.timer.stop();
                    new SnakeMain();
                    break;
                case "EXIT":
                    System.out.println("exit");
                    System.exit(1);
                    break;
                case "SETTINGS":
                    Variables.activity = 2;//TO DO
                    break;
            }
        }
    }
}
