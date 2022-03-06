package view;

import javax.swing.*;

import model.Counter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class MainFrame{
	DrawCircle dr = new DrawCircle();
	JLabel heading = new JLabel("My drawing");
	public static JLabel jL, N1, p1N, N2, p2N, N3, p3N, N4, p4N, p1C, p2C, p3C, p4C;
//	public static JPanel jP;
	public static JPanel jP2;
	public static JFrame jF;
	public static Graphics g;
	public static Color c;
	
	public MainFrame(String[] playerNames, int[] countersAvailable) {
			
		jF = new JFrame();
		jF.setTitle("Play Agony Aunt");
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.setResizable(false);
		
		jF.setSize(600,550);
//		JPanel jP = dc.getjP();
//		jF.add(jP);	
//		
//		jP.setLayout(null);
			
		jL = new JLabel("Player information");
		jL.setBounds(400,1,200,15);
		jF.add(jL);		
		
		//Display Player1 name	
		N1 = new JLabel("1");
		N1.setBounds(400,12,300,30);
		jF.add(N1);
		p1N = new JLabel(playerNames[0]);
		p1N.setBounds(420,12,300,30);
		jF.add(p1N);
			
		//Display Player2 name
		N2 = new JLabel("2");
		N2.setBounds(400,32,300,30);
		jF.add(N2);
		p2N = new JLabel(playerNames[1]);
		p2N.setBounds(420,32,300,30);
		jF.add(p2N);
		
		//Display Player3 name
		N3 = new JLabel("3");
		N3.setBounds(400,52,300,30);
		jF.add(N3);
		p3N = new JLabel(playerNames[2]);
		p3N.setBounds(420,52,300,30);
		jF.add(p3N);
		
		//Display Player4 name
		N4 = new JLabel("4");
		N4.setBounds(400,72,300,30);
		jF.add(N4);
		p4N = new JLabel(playerNames[3]);
		p4N.setBounds(420,72,300,30);
		jF.add(p4N);

		//Display Available counters for player1
		p1C = new JLabel("");
		p1C.setBounds(520,16,20,20);
		jF.add(p1C);
		p1C.setText(String.valueOf(countersAvailable[0]));
		
		//Display Available counters for player2
		p2C = new JLabel("");
		p2C.setBounds(520,37,20,20);
		jF.add(p2C);
		p2C.setText(String.valueOf(countersAvailable[1]));
				
		//Display Available counters for player3
		p3C = new JLabel("");
		p3C.setBounds(520,58,20,20);
		jF.add(p3C);
		p3C.setText(String.valueOf(countersAvailable[2]));
				
		//Display Available counters for player1
		p4C = new JLabel("");
		p4C.setBounds(520,78,20,20);
		jF.add(p4C);
		p4C.setText(String.valueOf(countersAvailable[3]));
		
		//Display counters
		jF.add(dr);
		
		jF.setVisible(true);	
			
	}
	
}
