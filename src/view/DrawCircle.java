package view;
import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JPanel {

//JPanel jP = new JPanel();

@Override
public void paintComponent(Graphics g)
{
 super.paintComponent(g);
 g.setColor(Color.RED);
 g.fillOval(500,20,15,15);
 g.drawOval(500,20,15,15);
 
 g.setColor(Color.YELLOW);
 g.fillOval(500,40,15,15);
 g.drawOval(500,40,15,15);

 g.setColor(Color.GREEN);
 g.fillOval(500,60,15,15);
 g.drawOval(500,60,15,15);
 
 g.setColor(Color.BLUE);
 g.fillOval(500,80,15,15);
 g.drawOval(500,80,15,15);

}

public void getCircle() {
	repaint();
}
}