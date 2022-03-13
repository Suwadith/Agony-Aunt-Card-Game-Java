package view;

import javax.swing.*;
import model.*;
import java.awt.*;

public class PenaltyBoardFrame extends JFrame{

	public static JLabel pS1, pS2, pS3, pS4, pS5, pS6, pS7, pS8, pS9; 
	private JPanel mainPanel, subPanel1, subPanel2, subPanel3, subPanel4, subPanel5, subPanel6, subPanel7, subPanel8, subPanel9;
	
	public PenaltyBoardFrame(){
		setTitle("Penalty Board");
		
		mainPanel = new JPanel(); 
		mainPanel.setLayout(new GridLayout(3, 4));
		
		//Joker square
		subPanel1 = new JPanel();
//		{
//			@Override
//			public void paintComponent(Graphics g)
//			{
//				super.paintComponent(g);
//				g.setColor(Color.RED);
//				g.fillOval(8,20,15,15);
//				g.drawOval(8,20,15,15); } 
//			};
		String jokerSquare = "src\\view\\PenaltyBoard\\jokersquare.jpg";
		ImageIcon j_icon = new ImageIcon(new ImageIcon(jokerSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
		subPanel1.add(new JLabel(j_icon, SwingConstants.CENTER));
		subPanel1.setBackground(Color.white);
		subPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
		//Spades square
	    subPanel2 = new JPanel()
	    {
			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.GREEN);
				g.fillOval(8,20,15,15);
				g.drawOval(8,20,15,15); }
			};
	    String spadesSquare = "src\\view\\PenaltyBoard\\spadessquare.jpg";
	    ImageIcon s_icon = new ImageIcon(new ImageIcon(spadesSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel2.add(new JLabel(s_icon, SwingConstants.CENTER));
	    subPanel2.setBackground(Color.white);
	    subPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	      
	    //Omega square
	    subPanel3 = new JPanel();
//	    {
//			@Override
//			public void paintComponent(Graphics g)
//			{
//				super.paintComponent(g);
//				g.setColor(Color.BLUE);
//				g.fillOval(8,20,15,15);
//				g.drawOval(8,20,15,15); }
//			};	
	    String omegaSquare = "src\\view\\PenaltyBoard\\omegasquare.jpg";
	    ImageIcon o_icon = new ImageIcon(new ImageIcon(omegaSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel3.add(new JLabel(o_icon, SwingConstants.CENTER));
	    subPanel3.setBackground(Color.white);
	    subPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Heart square
	    subPanel4 = new JPanel();
//	    {
//			@Override
//			public void paintComponent(Graphics g)
//			{
//				super.paintComponent(g);
//				g.setColor(Color.YELLOW);
//				g.fillOval(8,20,15,15);
//				g.drawOval(8,20,15,15); }
//			};	
	    String heartSquare = "src\\view\\PenaltyBoard\\heartssquare.jpg";
	    ImageIcon h_icon = new ImageIcon(new ImageIcon(heartSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel4.add(new JLabel(h_icon, SwingConstants.CENTER));
	    subPanel4.setBackground(Color.white);
	    subPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Queen square
	    subPanel5 = new JPanel();
	    String queenSquare = "src\\view\\PenaltyBoard\\queensquare.jpg";
	    ImageIcon q_icon = new ImageIcon(new ImageIcon(queenSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel5.add(new JLabel(q_icon, SwingConstants.CENTER));
	    subPanel5.setBackground(Color.white);
	    subPanel5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Diamond square
	    subPanel6 = new JPanel();
	    String diamondSquare = "src\\view\\PenaltyBoard\\diamondsquare.jpg";
	    ImageIcon d_icon = new ImageIcon(new ImageIcon(diamondSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel6.add(new JLabel(d_icon, SwingConstants.CENTER));
	    subPanel6.setBackground(Color.white);
	    subPanel6.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Plus square
	    subPanel7 = new JPanel();
	    String plusSquare = "src\\view\\PenaltyBoard\\plussquare.jpg";
	    ImageIcon p_icon = new ImageIcon(new ImageIcon(plusSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel7.add(new JLabel(p_icon, SwingConstants.CENTER));
	    subPanel7.setBackground(Color.white);
	    subPanel7.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Clubs square
	    subPanel8 = new JPanel();
	    String clubsSquare = "src\\view\\PenaltyBoard\\clubssquare.jpg";
	    ImageIcon c_icon = new ImageIcon(new ImageIcon(clubsSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel8.add(new JLabel(c_icon, SwingConstants.CENTER));
	    subPanel8.setBackground(Color.white);
	    subPanel8.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Hash square
	    subPanel9 = new JPanel();
	    String hashSquare = "src\\view\\PenaltyBoard\\hashsquare.jpg";
	    ImageIcon hash_icon = new ImageIcon(new ImageIcon(hashSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel9.add(new JLabel(hash_icon, SwingConstants.CENTER));
	    subPanel9.setBackground(Color.white);
	    subPanel9.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    mainPanel.add(subPanel1);
	    mainPanel.add(subPanel2);
	    mainPanel.add(subPanel3);
	    mainPanel.add(subPanel4);
	    mainPanel.add(subPanel5);
	    mainPanel.add(subPanel6);
	    mainPanel.add(subPanel7);
	    mainPanel.add(subPanel8);
	    mainPanel.add(subPanel9);
	    add(mainPanel);
	    setSize(400, 300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    
	}
}