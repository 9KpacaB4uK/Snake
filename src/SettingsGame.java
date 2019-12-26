import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SettingsGame {
    JButton checkbox = new JButton("");
    SettingsScreen SS = new SettingsScreen();
    public class DPanel extends JPanel {
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(Color.black);

        }
    }
    public class SettingsScreen extends JFrame {
        public SettingsScreen() throws HeadlessException {
            setSize(Variables.width_app,Variables.height_app);
            setBounds(Variables.centerX,Variables.centerY,Variables.width_app,Variables.height_app);
            DPanel dpanel = new DPanel();
            Font font1 = new Font("Arial", Font.BOLD, Variables.textsize);

            //create buttons
            Button butPlay,butBack;
            int butWidth = Variables.textsize * 10;
            int butHeight = Variables.textsize * 3;
            butPlay = new Button("PLAY");
            butPlay.setBounds(getSize().width/2-butWidth, getSize().height-butHeight*2, butWidth, butHeight);
            butPlay.setBackground(Color.green);
            butPlay.setFont(font1);
            butPlay.addActionListener(new Listener());

            butBack = new Button("BACK");
            butBack.setBounds(getSize().width/2, butPlay.getY(), butWidth, butHeight);
            butBack.setBackground(Color.RED);
            butBack.setFont(font1);
            butBack.addActionListener(new Listener());

            //settings
            JPanel settingsFrame = new JPanel();
            settingsFrame.setSize(getSize().width,butPlay.getY());
            settingsFrame.setBackground(Color.darkGray);

            //checkbox fullscreen
            JPanel checkboxPanel = new JPanel();
            checkboxPanel.setSize(Variables.blockW*8,Variables.blockH+10);
            checkboxPanel.setBounds(settingsFrame.getWidth()/2-checkboxPanel.getWidth()/2,settingsFrame.getHeight()/2,checkboxPanel.getWidth(),checkboxPanel.getHeight());
            checkboxPanel.setBackground(Color.blue);


            checkbox.setName("checkbox");
            checkbox.setSize(Variables.blockW,checkboxPanel.getHeight());
            checkbox.setBorder(new LineBorder(Color.red,1));

            if(Variables.fullscreen == false)
                checkbox.setBackground(Color.red);
            else
                checkbox.setBackground(Color.green);

            checkbox.setBounds(checkboxPanel.getX(),checkboxPanel.getY(),checkbox.getWidth(),checkbox.getHeight());
            checkbox.addActionListener(new Listener());

            Dimension textDimension = new Dimension(checkboxPanel.getWidth(), checkbox.getHeight());
            JTextField textField = new JTextField(10);
            textField.setPreferredSize(textDimension);
            textField.setMaximumSize(textDimension);
            textField.setText("FULLSCREEN");
            textField.setFont(font1);
            textField.setBorder(new LineBorder(Color.black,1));
            textField.setBackground(Color.black);
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setEditable(false);
            textField.setEnabled(false);
            textField.setBounds(checkbox.getX()+checkbox.getWidth(),checkboxPanel.getY(),checkboxPanel.getWidth(),checkboxPanel.getHeight());


            //add to display
            getContentPane().add(checkbox);
            getContentPane().add(textField);
            getContentPane().add(checkboxPanel);
            getContentPane().add(settingsFrame);
            getContentPane().add(butPlay);
            getContentPane().add(butBack);
            getContentPane().add(dpanel);
            if(checkbox.getBackground() == Color.green)
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            else
                setExtendedState(JFrame.NORMAL);
            setUndecorated(true);//without title bar
            setVisible(true);
        }
    }
    public class Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            switch (e.getActionCommand()){
                case "PLAY":
                    new SnakeMain();
                    SS.setVisible(false);
                    SS.dispose();
                    break;
                case "BACK":
                    new SnakeMenu();
                    SS.setVisible(false);
                    SS.dispose();
                    break;
            }
            if (e.getModifiers() == 16){//checkbox
                if(checkbox.getBackground()==Color.RED) {
                    Variables.fullscreen = true;
                    checkbox.setBackground(Color.green);
                    checkbox.setBorder(new LineBorder(Color.black,1));
                    Variables.width_app = Variables.width_Screen;
                    Variables.height_app = Variables.height_Screen;
                    Variables.blockUpdate();
                    SS.setSize(Variables.width_Screen,Variables.height_Screen);
                    SS.setBounds(0,0,SS.getWidth(),SS.getHeight());
                }
                else {
                    Variables.fullscreen = false;
                    checkbox.setBackground(Color.RED);
                    checkbox.setBorder(new LineBorder(Color.black,1));
                    Variables.width_app = 1280;
                    Variables.height_app = 720;
                    Variables.blockUpdate();
                    SS.setSize(Variables.width_app,Variables.height_app);
                    SS.setBounds(Variables.width_Screen/2-Variables.width_app/2,Variables.height_Screen/2-Variables.height_app/2,Variables.width_app,Variables.height_app);
                    //SS.setExtendedState(JFrame.NORMAL);
                }
                new SettingsGame();
                SS.setVisible(false);
                SS.dispose();
            }
        }
    }
}
