package view;

import model.Card;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static model.Suit.JOKER;

public class CardsWonFrame {

    public JFrame jF;
    public ArrayList<JLabel> cards = new ArrayList<>();
    public JPanel jP;
    public JScrollPane jSP;
//    public static ArrayList<ImageIcon> imageIcons;

    public CardsWonFrame(List<Card> totalCardsWon) {

        jP = new JPanel();
        jF = new JFrame();
        jP.setLayout(new GridLayout(0, 4));
        jSP = new JScrollPane(jP);
        jSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        jSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jF.setTitle("Agony Aunt - Cards Won");
        jF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jF.setResizable(true);

//        jSP.setSize(500, 350);

//        jP.setPreferredSize(new Dimension( 450,300));
//        jSP = new JScrollPane(jP);
//        jP.setAutoscrolls(true);
//        jSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        jSP.setPreferredSize(new Dimension( 450,300));
//        jF.add(jSP);

        jF.setSize(600, 450);
        jF.add(jSP);

//        jF.setLocationRelativeTo(null);
//        jP.setLayout(null);

        fetchImages(totalCardsWon);

//        int x = 50;
//        int y = 300;
//        for (int i = 0; i < cards.size(); i++) {
////            if (i % 4 == 0) {
////                y += 120;
////                x=50;
////                System.out.println("in");
////            }
//            cards.get(i).setBounds(x, y, 80, 110);
//            System.out.println(x + " " + y);
//            x += 85;
//        }


        for (JLabel j : cards) {
            jP.add(j);
        }

//        jP.setBounds(0,0,500,350);


        jP.repaint();

        jF.setLocationRelativeTo(null);
        jF.setVisible(true);


    }

    //get card image path
    public String returnCardImgPath(Card card) {
        if (card.getSuit() == JOKER) {
            return "src\\view\\Cards\\" + card.getSuit() + ".png";
        } else {
            if (card.getNumber() > 1 && card.getNumber() <= 10) {
                return "src\\view\\Cards\\" + card.getNumber() + "_of_" + card.getSuit() + ".png";
            } else {
                return "src\\view\\Cards\\" + card.getRank() + "_of_" + card.getSuit() + ".png";
            }
        }
    }

    public void fetchImages(List<Card> totalCardsWon) {
        for(Card card: totalCardsWon) {
            String imgPath = returnCardImgPath(card);
            ImageIcon iconImg = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
            cards.add(new JLabel(iconImg));
        }
    }

}
