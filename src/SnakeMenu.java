import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class SnakeMenu {
    MenuScreen ms = new MenuScreen();
    public class DPanel extends JPanel{
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(Color.black);
        }
    }
    public class MenuScreen extends JFrame {
        public MenuScreen() throws HeadlessException {
            setSize(Variables.width_app,Variables.height_app);
            setBounds(Variables.centerX,Variables.centerY,Variables.width_app,Variables.height_app);
            DPanel dpanel = new DPanel();
            Font font1 = new Font("Arial", Font.BOLD, Variables.textsize);

            //create buttons
            Button butPlay,butExit,butSettings;

            butPlay = new Button("PLAY");
            butPlay.setBounds(Variables.blockW*3, Variables.height_app/2-Variables.blockH*3/2, Variables.blockW*10, Variables.blockH*3);
            butPlay.setBackground(Color.green);
            butPlay.setFont(font1);

            butExit = new Button("EXIT");
            butExit.setBounds(butPlay.getX(), butPlay.getY() + butPlay.getHeight()+ Variables.blockH, butPlay.getWidth(), butPlay.getHeight());
            butExit.setBackground(Color.RED);
            butExit.setFont(font1);

            butSettings = new Button("SETTINGS");
            butSettings.setBounds(butPlay.getX(), butPlay.getY() - butPlay.getHeight()- Variables.blockH, butPlay.getWidth(), butPlay.getHeight());
            butSettings.setBackground(Color.yellow);
            butSettings.setFont(font1);

            butPlay.addActionListener(new Listener());
            butSettings.addActionListener(new Listener());
            butExit.addActionListener(new Listener());

            //add to display
            getContentPane().add(butSettings);
            getContentPane().add(butPlay);
            getContentPane().add(butExit);
            getContentPane().add(dpanel);
            if(Variables.fullscreen == true)
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            else
                setExtendedState(JFrame.NORMAL);
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
                    Variables.activity = 1;
                    new SnakeMain();
                    ms.setVisible(false);
                    ms.dispose();
                    break;
                case "EXIT":
                    System.out.println("exit");
                    System.exit(1);
                    break;
                case "SETTINGS":
                    Variables.activity = 2;//TO DO
                    new SettingsGame();
                    ms.setVisible(false);
                    ms.dispose();
                    break;
            }
        }
    }
}
