package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.awt.event.WindowListener;   

public class LoginFrame{

    public static JFrame jF;
    public static JLabel p1L, p2L, p3L, p4L;
    public static JPanel jP;
    public static JTextField p1T, p2T, p3T, p4T;
    public static JButton jBS;
    public static String player1, player2, player3, player4;

    public LoginFrame() {

        jP = new JPanel();
        jF = new JFrame();
        jF.setTitle("Agony Aunt - Player Registration");
        jF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jF.setResizable(false);

        jF.setSize(500, 350);
        jF.add(jP);

        jF.setLocationRelativeTo(null);
        jP.setLayout(null);


        p1L = new JLabel("Player 1: ");
        p1L.setBounds(100, 50, 80, 25);
        jP.add(p1L);
        p1T = new JTextField();
        p1T.setBounds(190, 50, 165, 25);
        jP.add(p1T);

        p2L = new JLabel("Player 2: ");
        p2L.setBounds(100, 80, 80, 25);
        jP.add(p2L);
        p2T = new JTextField();
        p2T.setBounds(190, 80, 165, 25);
        jP.add(p2T);

        p3L = new JLabel("Player 3: ");
        p3L.setBounds(100, 110, 80, 25);
        jP.add(p3L);
        p3T = new JTextField();
        p3T.setBounds(190, 110, 165, 25);
        jP.add(p3T);

        p4L = new JLabel("Player 4: ");
        p4L.setBounds(100, 140, 80, 25);
        jP.add(p4L);
        p4T = new JTextField();
        p4T.setBounds(190, 140, 165, 25);
        jP.add(p4T);
        
        jBS = new JButton("Start");
        jBS.setBounds(190, 200, 80, 25);
        jBS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1 = p1T.getText();
                player2 = p2T.getText();
                player3 = p3T.getText();
                player4 = p4T.getText();
                
                //Close frame before opening next frame
                jF.setVisible(false);
                jF.dispose();
                GameController gc = new GameController();
                gc.createPlayers(new String[]{player1, player2, player3, player4});

            }
        });
        jP.add(jBS);

        jF.setVisible(true);

    }
}


