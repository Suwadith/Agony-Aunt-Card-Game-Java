package model.Penalties;

import model.Card;
import model.Counter;
import model.DumpCard;
import model.Penalty;
import model.PenaltyBoard;

import java.util.Stack;

public class DumpthTrick extends Penalty {

    public DumpthTrick(Card dumpCard, Integer trickNumber, Stack<Counter> counters, PenaltyBoard penaltyBoard) {
        if(dumpCard.getNumber() == trickNumber) {
            String counterColor = counters.pop().getCounterColor().toString();
            char color = counterColor.charAt(0);
            //Call method to place counter
            super.placeCounter(color, 2, 2, penaltyBoard);
        }
    }

    @Override
    public boolean checkForPenalty(Card card) {
        return false;
    }

    @Override
    public boolean checkForPenalty(Card card, DumpCard dumpCard) {
        // TODO Auto-generated method stub
        return false;
    }
}
