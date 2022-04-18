package model.Penalties;

import model.Card;
import model.Counter;
import model.DumpCard;
import model.Penalty;
import model.PenaltyBoard;

import java.util.Stack;

public class LastTrick extends Penalty {

	public boolean penaltyPresent=false;
	public String penaltyCode;
	public String counterColor;
	
    public LastTrick(Integer trickNumber, Stack<Counter> counters, PenaltyBoard penaltyBoard) {
        if(trickNumber == 13) {
        	setPenalty();
            counterColor = counters.pop().getCounterColor().toString();
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
    
	@Override
	public void setPenalty() {
		this.penaltyCode = "LT";
		this.penaltyPresent=true;
	}

	public String getPenaltyCode() {
		return penaltyCode;
	}
	
	public String getCounterColor() {
		return counterColor;
	}
}
