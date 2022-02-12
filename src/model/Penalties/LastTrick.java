package model.Penalties;

import model.Card;
import model.Counter;
import model.DumpCard;
import model.Penalty;
import model.PenaltyBoard;

import java.util.Stack;

public class LastTrick extends Penalty {

    public LastTrick(Integer trickNumber, Stack<Counter> counters, PenaltyBoard penaltyBoard) {
        if(trickNumber == 13) {
            String counterColor = counters.pop().getCounterColor().toString();
            char color = counterColor.charAt(0);
            //Call method to place counter
            super.placeCounter(color, 0, 2, penaltyBoard);
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
