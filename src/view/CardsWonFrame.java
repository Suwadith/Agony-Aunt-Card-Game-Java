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

    public CardsWonFrame(List<Card> totalCardsWon) {

        jP = new JPanel();
        jF = new JFrame();
        jP.setLayout(new GridLayout(0, 4));
        jSP = new JScrollPane(jP);
        jSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jF.setTitle("Agony Aunt - Cards Won");
        jF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jF.setResizable(true);

        jF.setSize(600, 450);
        jF.add(jSP);

        fetchImages(totalCardsWon);

        for (JLabel j : cards) {
            jP.add(j);
        }

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
